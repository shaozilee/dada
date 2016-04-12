package com.shaozilee.dada.utils;


import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class GeneratePojoMysql {

    private String packageOutPath = "com.shaozilee.bed.pojo";//指定实体生成所在包的路径
    private String[] colnames; // 列名数组
    private String[] colTypes; //列名类型数组
    private int[] colSizes; //列名大小数组
    private boolean f_util = false; // 是否需要导入包java.util.*
    private boolean f_sql = false; // 是否需要导入包java.sql.*

    /*
     * 构造函数
     */
    public GeneratePojoMysql() throws Exception {
        DS.init();
        List tables = this.getTables();
        for (int i = 0; i < tables.size(); i++) {
            this.generatePojoClass(tables.get(i).toString());
        }

        DS.destory();
    }

    private void generatePojoClass(String tablename) throws Exception {
        System.out.println("tablename:"+tablename);
        /**/
        //创建连接
        Connection con = DS.getConnection();
        //查要生成实体类的表
        String sql = "select * from " + tablename;
        PreparedStatement pStemt = con.prepareStatement(sql);
        ResultSetMetaData rsmd = pStemt.getMetaData();
        int size = rsmd.getColumnCount();    //统计列
        colnames = new String[size];
        colTypes = new String[size];
        colSizes = new int[size];

        StringBuffer colBuffer = new StringBuffer();

        for (int i = 0; i < size; i++) {
            colnames[i] = rsmd.getColumnName(i + 1);
            colTypes[i] = rsmd.getColumnTypeName(i + 1);

            if (colTypes[i].equalsIgnoreCase("datetime")) {
                f_util = true;
            }
            if (colTypes[i].equalsIgnoreCase("image") || colTypes[i].equalsIgnoreCase("text")) {
                f_sql = true;
            }
            colSizes[i] = rsmd.getColumnDisplaySize(i + 1);

            colBuffer.append(colnames[i]+",");
        }

        String colStr = colBuffer.toString().substring(0,colBuffer.length()-1);

        String pojoClazz = initcap(tablename);

        //////
        StringBuffer sb = new StringBuffer();

        //判断是否导入工具包
        if (f_util) {
            sb.append("import java.util.Date;\r\n");
        }
        if (f_sql) {
            sb.append("import java.sql.*;\r\n");
        }
        sb.append("package " + this.packageOutPath + ";\r\n");
        sb.append("\r\n");

        sb.append("/**\r\n");
        sb.append(" *\r\n");
        sb.append(" * SELECT "+colStr+" FROM "+tablename+"\r\n");
        sb.append(" *\r\n");
        sb.append(" **/\r\n");
        //实体部分
        sb.append("\r\n\r\npublic class " + pojoClazz + "{\r\n");
        for (int i = 0; i < colnames.length; i++) {
            sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " " + colnames[i] + ";\r\n");
        }
        for (int i = 0; i < colnames.length; i++) {
            sb.append("\tpublic void set" + initcap(colnames[i]) + "(" + sqlType2JavaType(colTypes[i]) + " " +
                      colnames[i] + "){\r\n");
            sb.append("\tthis." + colnames[i] + "=" + colnames[i] + ";\r\n");
            sb.append("\t}\r\n");
            sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get" + initcap(colnames[i]) + "(){\r\n");
            sb.append("\t\treturn " + colnames[i] + ";\r\n");
            sb.append("\t}\r\n");
        }
        sb.append("}\r\n");

        /////
        String content = sb.toString();

        File directory = new File("");
        String path = this.getClass().getResource("").getPath();

        System.out.println(path);
        String outputPath = directory.getAbsolutePath() + "/bed/src/main/java/" + this.packageOutPath.replace(".", "/") + "/" +
                            pojoClazz + ".java";

        System.out.println("outputPath:"+outputPath);

        FileWriter fw = new FileWriter(outputPath);
        PrintWriter pw = new PrintWriter(fw);
        pw.println(content);
        pw.flush();
        pw.close();

        con.close();

    }


    /**
     * 获取数据库中的所有的表
     *
     * @return
     */
    private List getTables() throws Exception {
        List list = new ArrayList();
        Connection con = DS.getConnection();
        DatabaseMetaData dbMetData = con.getMetaData();
        ResultSet rs = dbMetData.getTables(null, null, null, new String[] { "TABLE", "VIEW" });

        while (rs.next()) {
            String type = rs.getString(4);
            if (type != null
                && (type.equalsIgnoreCase("TABLE") || type.equalsIgnoreCase("VIEW"))) {
                String tableName = rs.getString(3).toLowerCase();
                list.add(tableName);
            }
        }
        con.close();
        return list;
    }

    /**
     * 功能：将输入字符串的首字母改成大写
     *
     * @param str
     *
     * @return
     */
    private String initcap(String str) {
        String[] strArr = null;
        if(str.indexOf("_") != -1){
            strArr = str.split("_");
        }else{
            strArr = new String[]{str};
        }

        String className = "";

        for(String s : strArr){
            char[] ch = s.toCharArray();
            if (ch[0] >= 'a' && ch[0] <= 'z') {
                ch[0] = (char) (ch[0] - 32);
            }
            className+=(new String(ch));
        }
        return className;
    }

    /**
     * 功能：获得列的数据类型
     *
     * @param sqlType
     *
     * @return
     */
    private String sqlType2JavaType(String sqlType) {


        if (sqlType.equalsIgnoreCase("bit")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("tinyint")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            return "Short";
        } else if (sqlType.equalsIgnoreCase("int")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            return "Long";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "Float";
        } else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
                   || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")
                   || sqlType.equalsIgnoreCase("smallmoney")) {
            return "Double";
        } else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
                   || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
                   || sqlType.equalsIgnoreCase("text")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("datetime")) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase("image")) {
            return "Blod";
        }

        return null;
    }

    /**
     * 出口
     *
     * @param args
     */
    public static void main(String[] args) {
        try{
            new GeneratePojoMysql();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}