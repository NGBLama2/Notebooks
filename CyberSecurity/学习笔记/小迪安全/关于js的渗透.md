# 关于JS开发的网站的信息打点

- 后端语言：php java python .net

- 前端语言：javascript

## js开发 or 不是js开发

### js开发

js开发的网站，你可以在浏览器直观的看到代码是怎么样的，我们能够直接拿到源码，可以对其功能进行分析，比如它是如何实现某一个功能的，是如何传递数据的，加密的方式是什么...

### 不是js开发

使用上述的后端语言进行开发，在前端（浏览器）无法直接看到真是的代码，所以就需要通过抓包去看下能够看到的再去分析，或是通过源码泄露等手段去获取源码再进行分析与渗透

### 区别

js与其他后端语言的区别就是，js可以在前端（浏览器）直接看到（真实代码），所以不用考虑通过源码泄露拿到源码

## 如何判断是什么脚本语言开发的

使用浏览器插件wappalyzer即可

![image-20241210142326190](https://pic.hibugs.net/NGBTEAM/image-20241210142326190.png?imageSlim)

- 源程序代码简短
- 引入多个js文件
- 一般有/static/js/app.js等顺序的js文件
- 一般cookie中有connect.sid

## JS安全问题

- 源码泄露
- 未授权访问：从JS里面分析到更多的URL路径，尝试去访问若不用验证就能直接访问到则涉及到未授权
- 敏感key泄露：JS文件中可能会有一些配置信息（云应用、短信、邮件、数据库等）
- API接口安全：代码是如何加密的，如何传参的，已经更多的接口（URL）路径

## js的信息打点

### 能在js中找到什么？

一般可以在js中寻找更多的URL地址，在js代码逻辑（加密算法，APIKey配置，验证逻辑等）进行后期安全测试...

### js打点的方法（js前端架构）

#### 手工搜索分析

特点：时间长，分析起来麻烦，但是比较精准

##### 方法

一般我们使用浏览器的网络模块，或是抓包分析，**首先就是排除其他的一些包，专注于js文件**；其次**观察js文件的命名，一般我们会优先关注admin.js、menu.js、login.js...等这种js文件**（如下图）他们基本上会是这个网站的核心代码文件，下图就可以从login.js信息收集到这套源码时github上的开源框架

![image-20241204235010026](https://pic.hibugs.net/NGBTEAM/image-20241204235010026.png)

##### 如何快速获取价值信息？

可以在浏览器网络模块中筛选出js文件，然后搜索以下关键词

```javascript
src=
path=
method:"get"
http.get("
method:"post"
http.post("
$.ajax
http://service.httppost
         httppost
http://service.httpget
         httpget
```

这些关键词附近可能就会包含一些URL

###### 获取这些URL有什么用？

假设我们获取到一个URL：http://example.com/admin/userList/xxxx/xxxx或者是/admin/userList/xxxx/xxx

我们可以尝试拼接路径，如果直接访问到后台或是某些需要登陆后才能访问的页面，那么这就是一个未授权！

#### 半自动Burp分析

特点：手工和自动折中

##### 方法

Burpsuite自带Find scripts模块

![image-20241205033009434](https://pic.hibugs.net/NGBTEAM/image-20241205033009434.png)



#### 自动化项目分析

特点：时间段，基本不需要人为，但是精准度较差

##### 浏览器插件：FindSomething

![image-20241209130140210](https://pic.hibugs.net/NGBTEAM/image-20241209130140210.png?imageSlim)

##### 自动化脚本

###### JSFinder

不是很推荐，比较老了不太好用

安装地址：[GitHub - Threezh1/JSFinder: JSFinder is a tool for quickly extracting URLs and subdomains from JS files on a website.](https://github.com/Threezh1/JSFinder)

###### URLFinder *

好用，推荐

安装地址：[GitHub - pingc0y/URLFinder: 一款快速、全面、易用的页面信息提取工具，可快速发现和提取页面中的JS、URL和敏感信息。](https://github.com/pingc0y/URLFinder)

命令：`URLFinder.exe -u [地址，带http或https] -s [状态码（可以指定单个或几个状态码）或者all（表示输出所有状态码的页面）] -m 2`

###### JSINFO-SCAN

跟URLFinder差不多，选择一个即可

#### Fuzz

##### ffuf

功能强大的FUZZ工具，用来FUZZ js文件

字典下载网址：https://wordlists.assetnote.io

##### Packer-Fuzzer

针对Webpack等前端打包工具构造的网站快速、搞笑安全检测扫描工具

Webpack正好在js的打包中用的多

安装地址：[GitHub - rtcatc/Packer-Fuzzer: Packer Fuzzer is a fast and efficient scanner for security detection of websites constructed by javascript module bundler such as Webpack.](https://github.com/rtcatc/Packer-Fuzzer)

基础语法：

`python PackerFuzzer.py -u [地址（带http或https）]`

#### 思路总结

可以使用Fuzz爆破出更多的js路径，再使用别的工具去分析这些路径存在的情况，比如访问这些路径会不会直接跳转进需要登陆后才能看到的页面，来判定是否存在未授权

## 总结

1. 如何从表现出来的js提取价值的信息
2. 如何使用FUZZ爆出未表现出来的js信息
3. 如何对webpack进行测试
