搜聊
==============

XMPP开源服务端Openfire在Windows下的安装[https://blog.csdn.net/coleflowers/article/details/53557751]
--------------------------------

* 首先安装mysql数据库[https://dev.mysql.com/downloads/windows/], 安装包[https://dev.mysql.com/downloads/installer/]
* 其次安装nginx windows下的nginx[http://nginx.org/en/docs/windows.html]
* 然后安装openfire

修改src\com\kekexun\soochat\smack\BaseIMServer.java中的host、port、serverName