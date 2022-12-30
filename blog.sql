/*
 Navicat Premium Data Transfer

 Source Server         : SSM
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 30/12/2022 16:49:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_blog
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `first_Picture` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '首图',
  `flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签/原创/转载/引用',
  `views` int(11) NULL DEFAULT NULL COMMENT '浏览量',
  `commentabled` bit(1) NULL DEFAULT NULL COMMENT '是否可评论',
  `published` bit(1) NULL DEFAULT NULL COMMENT '是否发表',
  `recommendned` bit(1) NULL DEFAULT NULL COMMENT '是否推荐',
  `createTime` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `editTime` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改时间',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT 'userid',
  `description` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '博客描述',
  `type` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0文章,1友链,2关于我',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `t_blog_t_user`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_blog
-- ----------------------------
INSERT INTO `t_blog` VALUES (1532221761548345346, '关于我', '## &#x1F601;关于我\r\n我是Joe,一名大三学生，一个菜狗，一直做着自己喜欢的事情.\r\n\\\r\n技术方向主要是Java后端开发，喜欢思考，也是一名游戏发烧友.\r\n\\\r\n开发这个博客主要是为了学习分享以及记录我的生活,也顺便打磨一下自己之前所学的技术.\r\n\\\r\n此时浏览这段话的你也知道，学习是一件极其枯燥而无聊的过程，要走的路还很远,共勉.\r\n\\\r\n源码在首页第一条博客\r\n## &#x1F31F; 技能\r\n- 语言：Java、C\r\n- 数据库：MySQL、Redis\r\n- 开发框架：Springcloud、Springboot、SSM、Vue\r\n- 工具：Maven、git\r\n- 操作系统：Linux\r\n- IDE：IDEA、Hbuilder\r\n\r\n<div class=\"ui yellow message\">\r\n  <i class=\"star icon\"></i>\r\n一个人静静坐在电脑面前写代码的感觉，那是什么感觉？那是武林高手闭关修炼的感觉。\r\n</div>', '关于我', '原创', 1693, b'1', b'1', b'0', '2022-06-02 12:45:09', '2022-12-29 17:04:19', 2022576839, '关于我', '2');
INSERT INTO `t_blog` VALUES (1533692693731246082, '实用工具/网站分享', '**收集了一些壁纸网站,实用工具,有趣的网站,技术文档**\r\n<div class=\"ui floating yellow message\">\r\n  <b>壁纸图片</b>\r\n</div>\r\n<div class=\"ui items\">\r\n  <div class=\"item\">\r\n    <a class=\"ui tiny image\" href=\"https://www.pexels.com/zh-cn\" target=\"_blank\">\r\n   <img src=\"https://www.pexels.com/assets/static/images/meta/favicon.ico\">\r\n        </a>\r\n    <div class=\"content\">\r\n      <a class=\"header\">pexels</a>\r\n      <div class=\"description\">\r\n        <p>才华横溢的摄影作者在这里免费分享最精彩的素材图片和视频。</p>\r\n      </div>\r\n    </div>\r\n  </div>\r\n\r\n <div class=\"item\">\r\n    <a class=\"ui tiny image\" href=\"https://wallhaven.cc/\" target=\"_blank\">\r\n   <img src=\"https://wallhaven.cc/images/layout/logo.png\">\r\n        </a>\r\n    <div class=\"content\">\r\n      <a class=\"header\">wallhaven</a>\r\n      <div class=\"description\">\r\n        <p>The best wallpapers on the Net!</p>\r\n      </div>\r\n    </div>\r\n  </div>\r\n\r\n<div class=\"item\">\r\n    <a class=\"ui tiny image\" href=\"https://www.logosc.cn/so/\" target=\"_blank\">\r\n   <img src=\"https://www.logosc.cn/img/so/search_logo.png\">\r\n        </a>\r\n    <div class=\"content\">\r\n      <a class=\"header\">搜图神器logosc</a>\r\n      <div class=\"description\">\r\n        <p>免费版权图片一键搜索!</p>\r\n      </div>\r\n    </div>\r\n  </div>\r\n\r\n</div>\r\n<div class=\"ui floating red message\">\r\n  <b>技术文档</b>\r\n</div>\r\n\r\n<div class=\"ui cards\">\r\n  <div class=\"card\">\r\n    <div class=\"content\">\r\n	<div class=\"header\"><a  href=\"https://www.matools.com/api/java8\" target=\"_blank\">Java在线api文档</a></div>\r\n      <div class=\"description\">Java™ Platform, Standard Edition 8\r\nAPI Specification</div>\r\n    </div>\r\n  </div>\r\n\r\n  <div class=\"card\">\r\n    <div class=\"content\">\r\n	<div class=\"header\"><a  href=\"https://mybatis.org/mybatis-3/zh/getting-started.html\" target=\"_blank\">Mybatis3官方文档</a></div>\r\n      <div class=\"description\">MyBatis 是一款优秀的持久层框架，它支持自定义 SQL、存储过程以及高级映射。</div>\r\n    </div>\r\n  </div>\r\n    <div class=\"card\">\r\n    <div class=\"content\">\r\n	<div class=\"header\"><a  href=\"https://jquery.cuishifeng.cn/index.html\" target=\"_blank\">JQuery API 中文文档</a></div>\r\n      <div class=\"description\">jQuery API 3.5.1 速查表 </div>\r\n    </div>\r\n  </div>\r\n    <div class=\"card\">\r\n    <div class=\"content\">\r\n	<div class=\"header\"><a  href=\"http://felord.cn/_doc/_springboot/2.1.5.RELEASE/_book/pages/boot-documentation.html#boot-documentation\" target=\"_blank\">Springboot中文文档</a></div>\r\n      <div class=\"description\">Spring Boot 参考指南提供了 html、pdf 和 epub 格式的文档。最新的副本可在docs.spring.io/spring-boot/docs/current/reference 获取。</div>\r\n    </div>\r\n  </div>\r\n      <div class=\"card\">\r\n    <div class=\"content\">\r\n	<div class=\"header\"><a  href=\"https://www.linuxcool.com/\" target=\"_blank\">Linux命令大全(手册)\r\n</a></div>\r\n      <div class=\"description\">准确，丰富，稳定，在技术之路上为您护航！</div>\r\n    </div>\r\n  </div>\r\n        <div class=\"card\">\r\n    <div class=\"content\">\r\n	<div class=\"header\"><a  href=\"https://www.thymeleaf.org/\" target=\"_blank\">Thymeleaf\r\n</a></div>\r\n      <div class=\"description\">Thymeleaf is a modern server-side Java template engine for both web and standalone environments.</div>\r\n    </div>\r\n  </div>\r\n          <div class=\"card\">\r\n    <div class=\"content\">\r\n	<div class=\"header\"><a  href=\"https://semantic-ui.com/\" target=\"_blank\">Semantic UI\r\n</a></div>\r\n      <div class=\"description\">Semantic作为一款开发框架，帮助开发者使用对人类友好的HTML语言构建优雅的响应式布局。</div>\r\n    </div>\r\n  </div>\r\n            <div class=\"card\">\r\n    <div class=\"content\">\r\n	<div class=\"header\"><a  href=\"https://semantic-ui.com/\" target=\"_blank\">Semantic UI\r\n</a></div>\r\n      <div class=\"description\">Semantic作为一款开发框架，帮助开发者使用对人类友好的HTML语言构建优雅的响应式布局。</div>\r\n    </div>\r\n  </div>\r\n    <div class=\"card\">\r\n    <div class=\"content\">\r\n	<div class=\"header\"><a  href=\"https://www.redis.net.cn/order/\" target=\"_blank\">Redis命令手册\r\n</a></div>\r\n      <div class=\"description\">Redis是一个开源（BSD许可），内存存储的数据结构服务器，可用作数据库，高速缓存和消息队列代理。\r\n</div>\r\n    </div>\r\n  </div>\r\n  </div>\r\n<div class=\"ui floating orange message\">\r\n  <b>学习</b>\r\n</div>\r\n<div class=\"ui cards\">\r\n  <div class=\"card\">\r\n    <div class=\"content\">\r\n	<div class=\"header\"><a  href=\"https://www.bilibili.com/\" target=\"_blank\">哔哩哔哩</a></div>\r\n      <div class=\"description\">众所周知b站是个学习网站</div>\r\n    </div>\r\n  </div>\r\n  <div class=\"card\">\r\n    <div class=\"content\">\r\n  	<div class=\"header\"><a  href=\"https://www.imooc.com/\" target=\"_blank\">慕课网</a></div>\r\n      <div class=\"description\">很多免费的课程,总体来说质量还是不错的!</div>\r\n    </div>\r\n  </div>\r\n  <div class=\"card\">\r\n    <div class=\"content\">\r\n  	<div class=\"header\"><a  href=\"https://www.runoob.com/\" target=\"_blank\">菜鸟教程</a></div>\r\n      <div class=\"description\">学的不仅是技术，更是梦想！</div>\r\n    </div>\r\n  </div>  \r\n  <div class=\"card\">\r\n    <div class=\"content\">\r\n  	<div class=\"header\"><a  href=\"https://www.w3cschool.cn/tutorial\" target=\"_blank\">w3cschool</a></div>\r\n      <div class=\"description\">很多人编程入门教程都是从w3cschool学的.</div>\r\n    </div>\r\n  </div>\r\n  <div class=\"card\">\r\n    <div class=\"content\">\r\n  	<div class=\"header\"><a  href=\"https://leetcode.cn/leetbook/\" target=\"_blank\">LeetBook-力扣</a></div>\r\n      <div class=\"description\">算法刷题必备</div>\r\n    </div>\r\n  </div>\r\n  <div class=\"card\">\r\n    <div class=\"content\">\r\n  	<div class=\"header\"><a  href=\"https://stackoverflow.com/\" target=\"_blank\">stackoverflow-堆栈溢出</a></div>\r\n      <div class=\"description\">stackoverflow作为全球最大的技术问答网站,在上面提问,很多bug都能解决</div>\r\n    </div>\r\n  </div>\r\n  </div>\r\n\r\n<div class=\"ui floating blue message\">\r\n  <b>实用工具</b>\r\n</div>\r\n<div class=\"ui middle aligned animated selection list\">\r\n  <div class=\"item\">\r\n     <i class=\"map marker icon\"></i>\r\n    <div class=\"content\">\r\n		  <a class=\"header\" href=\"https://tool.lu/tinyimage/\">图片优化工具</a>\r\n		  <div class=\"description\">减小图片体积，优化网页加载速度。</div>\r\n    </div>\r\n  </div>\r\n <div class=\"item\">\r\n    <i class=\"map marker icon\"></i>\r\n   <div class=\"content\">\r\n 		  <a class=\"header\" href=\"https://tool.lu/tinyimage/\">文字识别工具</a>\r\n 		  <div class=\"description\">免费的在线图片转文字工具，可以把你的图片中的文字内容识别出来</div>\r\n   </div>\r\n </div>\r\n <div class=\"item\">\r\n    <i class=\"map marker icon\"></i>\r\n   <div class=\"content\">\r\n 		  <a class=\"header\" href=\"https://www.php.cn/xiazai/tool/6\">Md5加密工具</a>\r\n 		  <div class=\"description\">可实现MD5在线加密</div>\r\n   </div>\r\n </div>\r\n <div class=\"item\">\r\n    <i class=\"map marker icon\"></i>\r\n   <div class=\"content\">\r\n 		  <a class=\"header\" href=\"https://cn.online-qrcode-generator.com/\">在线二维码生成</a>\r\n 		  <div class=\"description\">免费在线二维码生成器</div>\r\n   </div>\r\n </div>\r\n  <div class=\"item\">\r\n     <i class=\"map marker icon\"></i>\r\n    <div class=\"content\">\r\n  		  <a class=\"header\" href=\"https://cssgr.id/\">cssgr.id</a>\r\n  		  <div class=\"description\">如果你是前端开发人员，这是一个非常有用的网站。你可以首先指定所需的行数和列数，或者在给定的选项中进行选择，然后为其生成代码。这使你可以专注于设计的其他方面而不是布局。</div>\r\n    </div>\r\n  </div> \r\n  <div class=\"item\">\r\n     <i class=\"map marker icon\"></i>\r\n    <div class=\"content\">\r\n  		  <a class=\"header\" href=\"https://www.iconshock.com/svg-icons/\">iconshock</a>\r\n  		  <div class=\"description\">在设计的时候，我们都注重简约。不过，有时我们喜欢添加一些渐变效果，这样会显得图标层次更加丰富一些，而这个工具，就可以帮助我们提升工作效率，哪怕你没有设计能力，你也可以设计出漂亮的图标。</div>\r\n    </div>\r\n  </div>\r\n  <div class=\"item\">\r\n     <i class=\"map marker icon\"></i>\r\n    <div class=\"content\">\r\n  		  <a class=\"header\" href=\"https://algorithm-visualizer.org/\">algorithm-visualizer</a>\r\n  		  <div class=\"description\">代码可视化</div>\r\n    </div>\r\n  </div>\r\n  <div class=\"item\">\r\n     <i class=\"map marker icon\"></i>\r\n    <div class=\"content\">\r\n  		  <a class=\"header\" href=\"https://convertio.co/zh/\">convertio</a>\r\n  		  <div class=\"description\">文件转换器,将您的文件转换成任意格式</div>\r\n    </div>\r\n  </div>\r\n</div>', 'https://th.wallhaven.cc/small/x8/x8oxez.jpg', '原创', 491, b'1', b'1', b'1', '2022-06-06 14:10:07', '2022-07-20 15:18:50', 2022576839, '收集了一些壁纸网站,实用工具,有趣的网站,学习文档.壁纸图片\r\n免费素材图片 pexels\r\n高清壁纸网站 wallhaven\r\n搜图神器 图片优化工具\r\n 图片文字识别\r\n技术文档\r\n java在线api文档\r\nmybatis官方文档\r\nJQuery API\r\nspringboot中文文档', '0');
INSERT INTO `t_blog` VALUES (1533711484716539906, '本博客技术总结及更新', '## **1.技术栈**\r\n**个人博客系统 SpringBoot+Mybatisplus**\r\n\\\r\n**1.前端**\r\n - 1.JS框架：JQuery\r\n - 2.滚动监听插件:jQueryWaypoints\r\n - 3.UI框架：[semantic-ui](https://semantic-ui.com/),[Layui](https://layuion.com/docs/)\r\n - 4.Markdown编辑器:[editor.md](https://pandao.github.io/editor.md/)\r\n - 5.动画样式:[animate](https://animate.style/)\r\n - 6.文章目录:[tocbot](https://tscanlin.github.io/tocbot/)\r\n - 7.代码高亮:[prismjs](https://prismjs.com/)\r\n - 8.音乐盒子:[aplayer](https://aplayer.js.org/#/)\r\n \r\n**2.后端**\r\n - 1.核心框架:SpringBoot 2.7.0+MybatisPlus\r\n - 2.数据库:mysql、redis\r\n - 3.项目构建:jdk1.8、maven 3\r\n - 4.模板引擎:thymeleaf\r\n - 5.分页插件:PageHelper\r\n - 6.登录加密:MD5\r\n - 7.开发工具:IDEA  \r\n - 8.运行环境:腾讯云 Centos7 轻量应用服务器2核2G\r\n \r\n\r\n## **2.博客简介**\r\n*因为是个人博客,不需要做用户权限管理,就只有管理员用户,**关于我**和**友链**复用了文章详情页*\r\n\\\r\n**博客主页功能**\r\n - 1.查看文章信息：文章列表、文章标题、文章内容、发布时间、访问量以及评论等信息\r\n - 2.查看标签文章：标签列表、标签文章信息\r\n - 3.查看归档：按照文章时间发布顺序查看文章\r\n - 4.搜索文章：导航栏右边搜索框根据关键字搜索\r\n - 5.音乐播放：上一曲、下一曲、音量控制、播放顺序控制、查看歌词等\r\n - 6.评论：评论并回复\r\n - 7.查看友链：查看并访问博主在友链页面添加的友链连接\r\n\r\n**后台管理**\r\n - 1.文章管理:查询文章列表、新增文章、编辑文章、删除文章、搜索文章\r\n - 2.标签管理:查询标签列表、新增标签、删除标签\r\n - 3.评论管理:查询文章评论列表、删除评论\r\n\r\n## **3.数据库设计**\r\n博客详情表：t_blog\r\n\\\r\n标签表：t_tag\r\n\\\r\n用户数据表：t_user\r\n\\\r\n评论表：t_comment\r\n\\\r\n博客标签关联表:t_blog_tag_relation\r\n## **4.源码地址**\r\n[Github](https://github.com/JMuSi404/Joeblog \"Github\")\r\n## **5.后续更新**\r\n**2022.6.21**\r\n\\\r\n因为沉迷艾尔登法环,都忘记自己还有个博客站点了,本次修复了一些手机端显示的bug,新增了博客回复评论邮件提醒功能,正在考虑要不要加个说说功能\r\n\\\r\n**2022.7.4**\r\n\\\r\n之前页面访问量的实现是直接访问页面就对数据库访问量字段进行+1,数据库压力大,现改用Redis缓存,把访问量暂存在 Redis中,当有人访问页面的时候，就直接在Redis中执行 +1 的操作，然后再每隔30秒把 Redis 中的访问量的数值写入到数据库中就搞定了~\r\n\\\r\n**2022.8.26**\r\n\\\r\n暑假学习springcloud去了,很久没关注这个博客了,无意中看到别人博客评论功能可以通过qq自动拉取昵称和头像,觉得很不错,我顺便也做了一个!\r\n\r\n', 'https://th.wallhaven.cc/small/y8/y8pr1d.jpg', '原创', 1671, b'1', b'1', b'1', '2022-06-06 15:24:47', '2022-09-22 11:19:12', 2022576839, '1.技术栈\r\n个人博客系统 SpringBoot+Mybatisplus\r\n1.前端\r\n1.JS框架：JQuery\r\n2.滚动监听插件:jQueryWaypoints\r\n3.UI框架：semantic-ui,Layui\r\n4.Markdown编辑器:editor.md\r\n5.动画样式:animate\r\n6.文章目录:tocbot', '0');
INSERT INTO `t_blog` VALUES (1540952701972537346, 'Redis常用五大数据类型', '## 1.前置了解\r\n### 1.1 NoSQL概述\r\nNoSQL，泛指非关系型的数据库，NoSQL即Not-Only SQL，它可以作为关系型数据库的良好补充。\r\n\\\r\nNoSQL数据库都具有非常高的读写性能，尤其在大数据量下，同样表现优秀。这得益于它的无关系性，数据库的结构简单。\r\n还不依赖业务逻辑方式存储，而以简单的**key-value**模式存储。因此大大的增加了数据库的扩展能力:\r\n\\\r\n1.不遵循SQL标准。\r\n\\\r\n2.不支持ACID。\r\n\\\r\n3.远超于SQL的性能。\r\n### 1.2 NoSQL适用场景\r\n1.对数据高并发的读写\r\n\\\r\n2.海量数据的读写\r\n\\\r\n3.对数据高可扩展性的\r\n### 1.3 NoSQL不适用场景\r\n1.需要事务支持\r\n\\\r\n2.基于sql的结构化查询存储，处理复杂的关系,需要即席查询\r\n\\\r\n3.用不着sql的和用了sql也不行的情况，请考虑用NoSql\r\n### 1.4 常用Nosql数据库\r\n**Memcache**:很早出现的NoSql数据库,数据都在内存中，一般不持久化,支持简单的key-value模式，支持类型单一.\r\n一般是作为缓存数据库辅助持久化的数据库\r\n\\\r\n**MongoDB**:高性能、开源、模式自由(schema  free)的文档型数据库,数据都在内存中， 如果内存不足，把不常用的数据保存到硬盘,虽然是key-value模式，但是对value（尤其是json）提供了丰富的查询功能,支持二进制数据及大型对象.\r\n可以根据数据的特点替代RDBMS成为独立的数据库。或者配合RDBMS，存储特定的数据。\r\n\\\r\n**Redis**:几乎覆盖了Memcached的绝大部分功能,数据都在内存中，支持持久化，主要用作备份恢复,除了支持简单的key-value模式，还支持多种数据结构的存储，比如 list、set、hash、zset等。\r\n一般是作为缓存数据库辅助持久化的数据库\r\n\r\n## 2.Redis简介\r\nredis是一个开源的、使用C语言编写的、支持网络交互的、可基于内存也可持久化的Key-Value数据库。\r\n\\\r\nredis的作者叫Salvatore Sanfilippo,他的github地址是\r\n[http://github.com/antirez](http://github.com/antirez \"http://github.com/antirez\")\r\n\r\n### 2.1 概述\r\n1.跟Memcached类似，它支持存储的value类型相对更多，包括string(字符串)、list(链表)、set(集合)、zset(sorted set --有序集合)和hash（哈希类型）。\r\n\\\r\n2.这些数据类型都支持push/pop、add/remove及取交集并集和差集及更丰富的操作，而且这些操作都是原子性的。\r\n\\\r\n3.在此基础上，Redis支持各种不同方式的排序。\r\n\\\r\n4.与memcached一样，为了保证效率，数据都是缓存在内存中。\r\n\\\r\n5.区别的是Redis会周期性的把更新的数据写入磁盘或者把修改操作写入追加的记录文件。\r\n\\\r\n6.并且在此基础上实现了master-slave(主从)同步。\r\n\r\n### 2.2 基本命令\r\nRedis默认16个数据库，类似数组下标从0开始，初始默认使用0号库.\r\n\\\r\n使用命令 select XX 来切换数据库。如: select 8\r\n统一密码管理，所有库同样密码。\r\n\\\r\ndbsize查看当前数据库的key的数量\r\n\\\r\nflushdb清空当前库\r\n\\\r\nflushall清空全部库\r\n\r\n## 3.Redis常用五大数据类型\r\nredis常见数据类型操作命令:[点我一下](http://www.redis.cn/commands.html \"点我一下\")\r\n### 3.1 Redis键(key)\r\nkeys *查看当前库所有key  (匹配：keys *1)\r\n\\\r\nexists key判断某个key是否存在\r\n\\\r\ntype key 查看你的key是什么类型\r\n\\\r\ndel key       删除指定的key数据\r\n\\\r\nunlink key   根据value选择非阻塞删除\r\n\\\r\n仅将keys从keyspace元数据中删除，真正的删除会在后续异步操作。\r\n\\\r\nexpire key 10   10秒钟：为给定的key设置过期时间\r\nttl key 查看还有多少秒过期，-1表示永不过期，-2表示已过期\r\n### 3.2 Redis字符串(String)\r\nString是Redis最基本的类型，你可以理解成与Memcached一模一样的类型，一个key对应一个value。\r\n\\\r\nString类型是二进制安全的。意味着Redis的string可以包含任何数据。比如jpg图片或者序列化的对象。\r\n\\\r\nString类型是Redis最基本的数据类型，一个Redis中字符串value最多可以是512M\r\n\\\r\n**3.2.1常用命令**\r\n\\\r\n**set**  key value ;添加键值对\r\n\\\r\n**get**   key ;查询对应键值\r\n\\\r\n**append**  key value ;将给定的value追加到原值的末尾\r\n\\\r\n**strlen**  key ;获得值的长度\r\n\\\r\n**setnx**  key value ;只有在 key 不存在时设置 key 的值\r\n\\\r\n**incr**  key ;将 key 中储存的数字值增1,只能对数字值操作，如果为空，新增值为1\r\n\\\r\n**decr**  key; 将 key 中储存的数字值减1,只能对数字值操作，如果为空，新增值为-1\r\n\\\r\n**incrby / decrby**  key 步长 ;将 key 中储存的数字值增减。自定义步长。\r\n\\\r\n**mset**  key1 value1 key2 value2...;同时设置一个或多个 key-value对\r\n\\\r\n**mget**  key1 key2 key3...;同时获取一个或多个 value\r\n\\\r\n**msetnx** key1 value1 key2 value2.....;同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在。\r\n\\\r\n**getrange**  key <起始位置><结束位置>;获得值的范围，类似java中的substring，前包，后包\r\n\\\r\n**setrange**  key <起始位置> value;用 value  覆写key所储存的字符串值，从<起始位置>开始(索引从0开始)。\r\n\\\r\n**setex**  key <过期时间> value;设置键值的同时，设置过期时间，单位秒。\r\n\\\r\n**getset** key value;以新换旧，设置了新值同时获得旧值。\r\n\r\n**3.2.2 数据结构**\r\n\\\r\nString的数据结构为简单动态字符串(Simple Dynamic String,缩写SDS)。是可以修改的字符串，内部结构实现上类似于Java的ArrayList，采用预分配冗余空间的方式来减少内存的频繁分配.\r\n\r\n### 3.3 Redis列表(List)\r\n\\\r\n单键多值\r\n\\\r\nRedis 列表是简单的字符串列表，按照插入顺序排序。你可以添加一个元素到列表的头部（左边）或者尾部（右边）。\r\n\\\r\n[![双向链表](https://img2.baidu.com/it/u=1732577027,2220368092&fm=253&fmt=auto&app=138&f=JPG?w=667&h=170 \"双向链表\")](https://img2.baidu.com/it/u=1732577027,2220368092&fm=253&fmt=auto&app=138&f=JPG?w=667&h=170 \"双向链表\")\r\n\\\r\n它的底层实际是个双向链表，对两端的操作性能很高，通过索引下标的操作中间的节点性能会较差。\r\n\r\n**3.3.1 常用命令**\r\n\\\r\n**lpush/rpush** <key><value1><value2><value3> ....; 从左边/右边插入一个或多个值。\r\n\\\r\n**lpop/rpop**  <key>;从左边/右边吐出一个值。值在键在，值光键亡。\r\n\\\r\n**rpoplpush**  <key1><key2>;从<key1>列表右边吐出一个值，插到<key2>列表左边。\r\n\\\r\n**lrange** <key><start><stop>;按照索引下标获得元素(从左到右),lrange mylist 0 -1   0左边第一个，-1右边第一个，（0-1表示获取所有）\r\n\\\r\n**lindex** <key><index>;按照索引下标获得元素(从左到右)\r\n\\\r\n**llen** <key>;获得列表长度\r\n\\\r\n**linsert** <key>  before <value><newvalue>;在<value>的后面插入<newvalue>插入值\r\n\\\r\n**lrem** <key><n><value>;从左边删除n个value(从左到右)\r\n\\\r\n**lset** <key><index><value>;将列表key下标为index的值替换成value\r\n\r\n**3.3.2 数据结构**\r\n\\\r\nList的数据结构为快速链表quickList。\r\n\\\r\n首先在列表元素较少的情况下会使用一块连续的内存存储，这个结构是ziplist，也即是压缩列表。\r\n\\\r\n它将所有的元素紧挨着一起存储，分配的是一块连续的内存。\r\n当数据量比较多的时候才会改成quicklist。\r\n\\\r\n因为普通的链表需要的附加指针空间太大，会比较浪费空间。比如这个列表里存的只是int类型的数据，结构上还需要两个额外的指针prev和next。\r\n\\\r\nRedis将链表和ziplist结合起来组成了quicklist。也就是将多个ziplist使用双向指针串起来使用。这样既满足了快速的插入删除性能，又不会出现太大的空间冗余。\r\n\r\n### 3.4.Redis集合(Set)\r\nRedis set对外提供的功能与list类似是一个列表的功能，特殊之处在于set是可以自动排重的，当你需要存储一个列表数据，又不希望出现重复数据时，set是一个很好的选择，并且set提供了判断某个成员是否在一个set集合内的重要接口，这个也是list所不能提供的。\r\n\\\r\nRedis的Set是string类型的无序集合。它底层其实是一个value为null的hash表，所以添加，删除，查找的复杂度都是O(1)。\r\n\\\r\n一个算法，随着数据的增加，执行时间的长短，如果是O(1)，数据增加，查找数据的时间不变\r\n\r\n**3.4.1 常用命令**\r\n\\\r\n**sadd** <key><value1><value2> .....;将一个或多个 member 元素加入到集合 key 中，已经存在的 member 元素将被忽略\r\n\\\r\n**smembers** <key>;取出该集合的所有值。\r\n\\\r\n**sismember** <key><value>;判断集合<key>是否为含有该<value>值，有1，没有0\r\n\\\r\n**scard** <key>;返回该集合的元素个数。\r\n\\\r\n**srem** <key><value1><value2> ....;删除集合中的某个元素。\r\n\\\r\n**spop** <key> ;随机从该集合中吐出一个值。\r\n\\\r\n**srandmember** <key><n> ;随机从该集合中取出n个值。不会从集合中删除 。\r\n\\\r\n**smove** <source><destination>;value把集合中一个值从一个集合移动到另一个集合\r\n\\\r\n**sinter** <key1><key2>;返回两个集合的交集元素。\r\n\\\r\n**sunion** <key1><key2>;返回两个集合的并集元素。\r\n\\\r\n**sdiff** <key1><key2>;返回两个集合的差集元素(key1中的，不包含key2中的)\r\n\r\n**3.4.2 数据结构**\r\n\\\r\nSet数据结构是dict字典，字典是用哈希表实现的。\r\n\\\r\nJava中HashSet的内部实现使用的是HashMap，只不过所有的value都指向同一个对象。Redis的set结构也是一样，它的内部也使用hash结构，所有的value都指向同一个内部值。\r\n\r\n### 3.5 Redis哈希(Hash)\r\nRedis hash 是一个键值对集合。\r\n\\\r\nRedis hash是一个string类型的field和value的映射表，hash特别适合用于存储对象。\r\n\\\r\n类似Java里面的Map<String,Object>,用户ID为查找的key，存储的value用户对象包含姓名，年龄，生日等信息，如果用普通的key/value结构来存储\r\n\r\n**3.5.1 常用命令**\r\n\\\r\n**hset** <key><field><value> ;给<key>集合中的  <field>键赋值<value>\r\n\\\r\n**hget** <key1><field>;从<key1>集合<field>取出 value\r\n\\\r\n**hmset** <key1><field1><value1><field2><value2>... ;批量设置hash的值\r\n\\\r\n**hexists** <key1><field>;查看哈希表 key 中，给定域 field 是否存在.\r\n\\\r\n**hkeys** <key>;列出该hash集合的所有field\r\n\\\r\n**hvals** <key>;列出该hash集合的所有value\r\n\\\r\n**hincrby** <key><field><increment>;为哈希表 key 中的域 field 的值加上增量 1   -1\r\n\\\r\n**hsetnx** <key><field><value>;将哈希表 key 中的域 field 的值设置为 value ，当且仅当域 field 不存在\r\n\r\n**3.5.2 数据结构**\r\n\\\r\nHash类型对应的数据结构是两种：ziplist（压缩列表），hashtable（哈希表）。当field-value长度较短且个数较少时，使用ziplist，否则使用hashtable。\r\n\r\n### 3.6 Redis有序集合Zset(sorted set)\r\nRedis有序集合zset与普通集合set非常相似，是一个没有重复元素的字符串集合。\r\n\\\r\n不同之处是有序集合的每个成员都关联了一个评分（score）,这个评分（score）被用来按照从最低分到最高分的方式排序集合中的成员。集合的成员是唯一的，但是评分可以是重复了 。\r\n\\\r\n因为元素是有序的, 所以你也可以很快的根据评分（score）或者次序（position）来获取一个范围的元素。\r\n\\\r\n访问有序集合的中间元素也是非常快的,因此你能够使用有序集合作为一个没有重复成员的智能列表。\r\n\r\n**3.6.1 常用命令**\r\n\\\r\n**zadd**  <key><score1><value1><score2><value2>…;\r\n将一个或多个 member 元素及其 score 值加入到有序集 key 当中。\r\n\\\r\n**zrange** <key><start><stop>  [WITHSCORES];\r\n返回有序集 key 中，下标在<start><stop>之间的元素\r\n带WITHSCORES，可以让分数一起和值返回到结果集。\r\n\\\r\n**zrangebyscore** key minmax [withscores] [limit offset count];\r\n返回有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。有序集成员按 score 值递增(从小到大)次序排列。\r\n\\\r\n**zrevrangebyscore** key maxmin [withscores] [limit offset count];\r\n同上，改为从大到小排列.\r\n\\\r\n**zincrby** <key><increment><value>;     为元素的score加上增量\r\n\\\r\n**zrem**  <key><value>删除该集合下，指定值的元素;\r\n\\\r\n**zcount** <key><min><max>;统计该集合，分数区间内的元素个数\r\n\\\r\n**zrank** <key><value>;返回该值在集合中的排名，从0开始。\r\n\r\n**3.6.2 数据结构**\r\n\\\r\nSortedSet(zset)是Redis提供的一个非常特别的数据结构，一方面它等价于Java的数据结构Map<String, Double>，可以给每一个元素value赋予一个权重score，另一方面它又类似于TreeSet，内部的元素会按照权重score进行排序，可以得到每个元素的名次，还可以通过score的范围来获取元素的列表。\r\n\\\r\nzset底层使用了两个数据结构\r\n\\\r\n（1）hash，hash的作用就是关联元素value和权重score，保障元素value的唯一性，可以通过元素value找到相应的score值。\r\n\\\r\n（2）跳跃表，跳跃表的目的在于给元素value排序，根据score的范围获取元素列表。\r\n[![跳跃表](https://img2.baidu.com/it/u=1017533201,3342115177&fm=253&fmt=auto&app=138&f=PNG?w=1395&h=500 \"跳跃表\")](https://img2.baidu.com/it/u=1017533201,3342115177&fm=253&fmt=auto&app=138&f=PNG?w=1395&h=500 \"跳跃表\")\r\n\r\n', 'https://w.wallhaven.cc/full/y8/wallhaven-y8vlyk.jpg', '原创', 303, b'1', b'1', b'0', '2022-06-26 14:58:47', '2022-12-22 18:17:21', 2022576839, '1.前置了解\r\n1.1 NoSQL概述\r\nNoSQL，泛指非关系型的数据库，NoSQL即Not-Only SQL，它可以作为关系型数据库的良好补充。\r\nNoSQL数据库都具有非常高的读写性能，尤其在大数据量下，同样表现优秀', '0');
INSERT INTO `t_blog` VALUES (1543806513464799233, '友链', '## &#x1F31F;友链列表\r\n<div class=\"ui link  special   six doubling cards\">\r\n  <div class=\"red  card\">\r\n    <div class=\"blurring dimmable image\">\r\n      <div class=\"ui dimmer\">\r\n        <div class=\"content\">\r\n          <div class=\"center\">\r\n            <div class=\"ui inverted button\">\r\n			<a href=\"https://www.zhangweicheng.xyz/\" target=\"_blank\">前往</a>\r\n</div>\r\n          </div>\r\n        </div>\r\n      </div>\r\n      <img src=\"https://www.zhangweicheng.xyz/logo.jpg\">\r\n    </div>\r\n    <div class=\"content\">\r\n      <a class=\"header\">VAE+</a>\r\n	   <div class=\"meta\">\r\n      </div>\r\n    </div>\r\n	  <div class=\"extra content\">\r\n      <a  href=\"#\" onclick=\"addBookmark(\'https://www.zhangweicheng.xyz\',\'buffer now\')\">\r\n        <i class=\"users icon\"></i>\r\n        加入书签\r\n      </a>\r\n    </div>\r\n</div>\r\n</div>\r\n<div class=\"ui floating message\">\r\n  <i class=\"star icon\"></i>\r\n需要交换友链的可在下方留言\r\n(｡･∀･)ﾉﾞ嗨\r\n</div>\r\n\r\n<div class=\"ui floating message\">\r\n  <i class=\"close icon\"></i>\r\n  <div class=\"header\">\r\n  <p>名称：Joe的小窝</p>\r\n<p>简介：往者不可谏,来者犹可追</p>\r\n<p>站点：https://www.hsjhome.top</p>\r\n<p>头像：https://www.hsjhome.top/img/blogAvatar.jpg</p>\r\n  </div>\r\n  </div>\r\n', '友链', '原创', 1275, b'1', b'1', b'0', '2022-07-04 11:58:50', '2022-12-29 17:04:29', 2022576839, '友链', '1');

-- ----------------------------
-- Table structure for t_blog_tag_relation
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_tag_relation`;
CREATE TABLE `t_blog_tag_relation`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `blog_id` bigint(20) NULL DEFAULT NULL COMMENT '博客id',
  `tag_id` bigint(20) NULL DEFAULT NULL COMMENT '标签id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `t_blog_id`(`blog_id`) USING BTREE,
  INDEX `t_tag_id`(`tag_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_blog_tag_relation
-- ----------------------------
INSERT INTO `t_blog_tag_relation` VALUES (1547438081169285121, 1533725535219703810, 1533670233673768962);
INSERT INTO `t_blog_tag_relation` VALUES (1547446292668493825, 1533727099271471105, 1533728860904316930);
INSERT INTO `t_blog_tag_relation` VALUES (1549655053738967042, 1533692693731246082, 1533670487127171073);
INSERT INTO `t_blog_tag_relation` VALUES (1572787569991680002, 1533711484716539906, 1533670682262970370);
INSERT INTO `t_blog_tag_relation` VALUES (1572977368908177278, 1533722458068574210, 1533670682262970370);
INSERT INTO `t_blog_tag_relation` VALUES (1572977368908177410, 1533722458068574210, 1533670233673768962);
INSERT INTO `t_blog_tag_relation` VALUES (1605828089244385282, 1605828089219219457, 1533670233673768962);
INSERT INTO `t_blog_tag_relation` VALUES (1605828089244385283, 1605828089219219457, 1533670520773877761);
INSERT INTO `t_blog_tag_relation` VALUES (1605828345533136897, 1605828345403113473, 1533670233673768962);
INSERT INTO `t_blog_tag_relation` VALUES (1605828345549914113, 1605828345403113473, 1533670487127171073);
INSERT INTO `t_blog_tag_relation` VALUES (1605857010316857345, 1605857010283302914, 1533670682262970370);
INSERT INTO `t_blog_tag_relation` VALUES (1605857010316857346, 1605857010283302914, 1533670487127171073);
INSERT INTO `t_blog_tag_relation` VALUES (1605861987911270401, 1605861987856744449, 1533670487127171073);
INSERT INTO `t_blog_tag_relation` VALUES (1605861987911270402, 1605861987856744449, 1533691395015344130);
INSERT INTO `t_blog_tag_relation` VALUES (1605862635205623809, 1605862628528291842, 1533670520773877761);
INSERT INTO `t_blog_tag_relation` VALUES (1605862635214012418, 1605862635159486466, 1533670520773877761);
INSERT INTO `t_blog_tag_relation` VALUES (1605862635226595330, 1605862628528291842, 1533691395015344130);
INSERT INTO `t_blog_tag_relation` VALUES (1605862635230789633, 1605862635159486466, 1533691395015344130);
INSERT INTO `t_blog_tag_relation` VALUES (1605863381066121218, 1605863381019983874, 1533670487127171073);
INSERT INTO `t_blog_tag_relation` VALUES (1605863381078704130, 1605863381019983874, 1533670682262970370);
INSERT INTO `t_blog_tag_relation` VALUES (1605864337644195841, 1605864337627418625, 1533670487127171073);
INSERT INTO `t_blog_tag_relation` VALUES (1605864337707110402, 1605864337627418625, 1533691395015344130);
INSERT INTO `t_blog_tag_relation` VALUES (1605864502937522179, 1605864502937522178, 1533670487127171073);
INSERT INTO `t_blog_tag_relation` VALUES (1605864503004631041, 1605864502937522178, 1533691395015344130);
INSERT INTO `t_blog_tag_relation` VALUES (1605864667844972546, 1605864667844972545, 1533670520773877761);
INSERT INTO `t_blog_tag_relation` VALUES (1605864667844972547, 1605864667844972545, 1533728860904316930);
INSERT INTO `t_blog_tag_relation` VALUES (1605869997446410241, 1605864557039849474, 1533670520773877761);
INSERT INTO `t_blog_tag_relation` VALUES (1605869997454798849, 1605864557039849474, 1533728860904316930);
INSERT INTO `t_blog_tag_relation` VALUES (1605870097195347970, 1540952701972537346, 1540950939798626305);
INSERT INTO `t_blog_tag_relation` VALUES (1605898261703438338, 1605898261640523777, 1533670233673768962);
INSERT INTO `t_blog_tag_relation` VALUES (1605898261766352897, 1605898261640523777, 1533670487127171073);
INSERT INTO `t_blog_tag_relation` VALUES (1607310062550339586, 1607310062508396545, 1533670233673768962);
INSERT INTO `t_blog_tag_relation` VALUES (1607310062550339587, 1607310062508396545, 1533670520773877761);
INSERT INTO `t_blog_tag_relation` VALUES (1607310062550339588, 1607310062508396545, 1533691395015344130);
INSERT INTO `t_blog_tag_relation` VALUES (1608388434886393858, 1532221761548345346, 1533670682262970370);
INSERT INTO `t_blog_tag_relation` VALUES (1608388476208676865, 1543806513464799233, 1533670682262970370);

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `nickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `createTime` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `blog_id` bigint(20) NULL DEFAULT NULL COMMENT '博客id',
  `extends_commentId` bigint(20) NULL DEFAULT NULL COMMENT '父类评论的id',
  `administrator` bit(1) NULL DEFAULT NULL COMMENT '是否为管理员评论',
  `extends_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父类评论的昵称',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `t_comment_t_blog`(`blog_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_comment
-- ----------------------------

-- ----------------------------
-- Table structure for t_image
-- ----------------------------
DROP TABLE IF EXISTS `t_image`;
CREATE TABLE `t_image`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `url` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '图片地址',
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_image
-- ----------------------------
INSERT INTO `t_image` VALUES (1608439656687398913, 'http://rmel8ulik.hn-bkt.clouddn.com/2056d4a4-04ee-4aa4-b6f4-774792c7718e.jpg', '9d883eb0c3af0548c5a0d207587971f.jpg');
INSERT INTO `t_image` VALUES (1608439805820071938, 'http://rmel8ulik.hn-bkt.clouddn.com/b677b576-a9b6-4b40-8b3c-b41fa217f2ca.jpg', '0b0ba6c0dc8c6ab2e05084f96a928c2.jpg');
INSERT INTO `t_image` VALUES (1608439817174052866, 'http://rmel8ulik.hn-bkt.clouddn.com/5e38e35d-f56d-4961-9a9e-5d8c203f86bd.jpg', '1b77328e91c91aaf8ddeac70fd0fdf8.jpg');
INSERT INTO `t_image` VALUES (1608439827693367298, 'http://rmel8ulik.hn-bkt.clouddn.com/772363ef-5c0d-4b89-81ff-80fa065037bb.jpg', '83c21cc0b0e2fb5733cb3eca06ed821.jpg');
INSERT INTO `t_image` VALUES (1608439835108896770, 'http://rmel8ulik.hn-bkt.clouddn.com/e05ef8c4-40fd-4cfb-88df-806e30f15604.jpg', '98fb44c92b22f9d0f285dae0d6e2f52.jpg');
INSERT INTO `t_image` VALUES (1608439845456244738, 'http://rmel8ulik.hn-bkt.clouddn.com/be76333d-dd73-4c0d-af04-6f8c5747c67e.jpg', '238f81c93576e0657be23d321fb0cfe.jpg');
INSERT INTO `t_image` VALUES (1608439854943760386, 'http://rmel8ulik.hn-bkt.clouddn.com/1b5539cf-f1ca-4f3f-b302-ead30b561037.jpg', '802dfe2e7519c39a5416d9cada679d0.jpg');
INSERT INTO `t_image` VALUES (1608439863399477249, 'http://rmel8ulik.hn-bkt.clouddn.com/0b636065-07f7-4c41-8dda-e09ba1b111c6.jpg', '1961dda72310ed5eb32d31a66746717.jpg');
INSERT INTO `t_image` VALUES (1608439872375287810, 'http://rmel8ulik.hn-bkt.clouddn.com/69f6dbd7-bae3-403b-9dd5-e063cd7f0728.jpg', '4686ba9c9a02ddadf7a497b8ac9a33f.jpg');
INSERT INTO `t_image` VALUES (1608439887369924610, 'http://rmel8ulik.hn-bkt.clouddn.com/6d4e5c8e-3eab-4328-926e-ca128f2e39cd.jpg', '21750b57b86c44c92c72a3dea7a3ee0.jpg');
INSERT INTO `t_image` VALUES (1608439896920354817, 'http://rmel8ulik.hn-bkt.clouddn.com/af28ab11-297d-4263-8403-378dd9b0fd3d.jpg', 'ab1eda12f72e8f9bf3a05ddff8d5dc3.jpg');
INSERT INTO `t_image` VALUES (1608439906219126785, 'http://rmel8ulik.hn-bkt.clouddn.com/bdbd387a-e9e9-4b51-8436-b40a278ac829.jpg', 'b22ad0029e58521cc0e8eceb7cf72aa.jpg');
INSERT INTO `t_image` VALUES (1608439941786824705, 'http://rmel8ulik.hn-bkt.clouddn.com/c17b7143-cf4f-479d-89ff-189fad390c1b.jpg', 'd26d1481a1aef1441689132f59716e9.jpg');
INSERT INTO `t_image` VALUES (1608439949730836482, 'http://rmel8ulik.hn-bkt.clouddn.com/2e9213b4-9c85-40cf-b2ce-dcaf05286003.jpg', 'd4912772bc4f23d873b9792a0f29496.jpg');
INSERT INTO `t_image` VALUES (1608439959319015426, 'http://rmel8ulik.hn-bkt.clouddn.com/271ebfe9-0eb6-4940-8241-475230b3b94f.jpg', 'dd02c9d293b1ff2b5fe9188a126018b.jpg');
INSERT INTO `t_image` VALUES (1608439967527268354, 'http://rmel8ulik.hn-bkt.clouddn.com/aa137493-294e-4aed-aee4-c65a0eafbdf0.jpg', 'f7d82abd0f370b4d10f51197049ce88.jpg');

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `count` int(11) NULL DEFAULT NULL COMMENT '计数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_tag
-- ----------------------------
INSERT INTO `t_tag` VALUES (1533670233673768962, 'bug与异常', NULL);
INSERT INTO `t_tag` VALUES (1533670487127171073, '工具/链接', NULL);
INSERT INTO `t_tag` VALUES (1533670520773877761, 'spring', NULL);
INSERT INTO `t_tag` VALUES (1533670682262970370, '闲言碎语', NULL);
INSERT INTO `t_tag` VALUES (1533691395015344130, 'Java', NULL);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `nickname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型',
  `createTime` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `editTime` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (2022576839, 'Joe', 'admin', '7c4a8d09ca3762af61e59520943dc26494f8941b', '1870562227@qq.com', 'http://rmel8ulik.hn-bkt.clouddn.com/myAvatar.jpg', NULL, '2022-5-4', NULL);

SET FOREIGN_KEY_CHECKS = 1;
