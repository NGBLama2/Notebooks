# 为什么要学习StringJoiner？

学习StringBuilder的时候说过了，使用StringBuilder可以用于字符的拼接和反转，那既然已经可以实现这些操作了，为什么又来了个StringJoiner呢？

StringBuilder确实是解决了我们字符串拼接麻烦的问题，但是使用StringBuilder不能指定中间用什么来分隔，也不能决定开头结尾是什么，都得额外的写，还是有些麻烦，因此引入了StringJoiner

## StringJoiner概述

StringJoiner跟StringBuilder一样，可以看作是一个容器，创建之后里面的内容是可变的

**作用：提高字符串的操作效率，且代码编写简洁，但是目前市场上很少有人用**

## 构造方法

|                 方法名                 |                   说明                   |
| :---------------------------------: | :------------------------------------: |
|      public StringJoiner(间隔符号)      |      创建一个StringJoiner对象，指定拼接时间隔符号      |
| public StringJoiner(间隔符号，开始符号，结束符号) | 创建一个StringJoiner对象，指定拼接时间隔符号，开始符号，结束符号 |

## 成员方法

| 方法名                            | 说明                     |
| ------------------------------ | ---------------------- |
| public StringJoiner add(添加的内容) | 添加数据，并返回对象本身           |
| public int length()            | 返回长度（字符出现的个数）          |
| public String toString()       | 返回一个字符串（该字符串就是拼接之后的结果） |

