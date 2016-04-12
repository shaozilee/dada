#如何配置指定的模板生效
模板是整体存放在webapp目录下的一个独立目录，里面包含.btl、css、js、资源等文件

配置dispatcher-context.xml：`<entry key="theme" value="default"></entry>`指定value的值为模板目录名字

配置dispatcher-context.xml：`<property name="prefix" value="/default/"></property>`指定value的值为`/模板目录名字/`

#如何测试模板btl页面
在TEST目录下新建指定主题对应的测试数据
+default-------------------theme目录
	+index.btl.js----------对应页面的测试数据
+common.js-----------------公共数据和参数部分

#访问测试
URL：/app/${theme}/index.btl

