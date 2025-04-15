# git基本操作⌨

## git中的文件状态🔱

​	git目录下的文件的修改（增加、删除、更新）会存在几个状态，而这些状态会随着git命令的执行而改变...

![image-20241020211710453](https://pic.hibugs.net/NGBTEAM/image-20241020211710453.png)

### 工作区

**未暂存：**修改已有的文件

**未跟踪：**新创建一个文件

​	以上两种状态可以通过`git add`命令变为已暂存

### 暂存区

**已暂存：**在工作区通过`git add`命令提交的文件

​	该文件暂时存在该位置，等待`git commit`命令提交到仓库

### 仓库

**提交记录：**在暂存区通过`git commit`命令提提交的文件

​	提交的这些文件会被当作一次commit，也可以成为提交记录，这里也就是我们所说的版本，倘若我们今天提交了commit1，之后我们又陆陆续续提交了很多代码，突然有一天发现代码写的不够好想要回溯到commit1时的代码版本，这时候我们就可以回溯到commit1时提交的文件代码

### 状态转化总览

​	工作区（git add）-> 暂存区（git commit）-> 仓库

## 命令实操（git add & git commit）

​	使用`touch`命令创建一个新的文件test01.txt，使用`git status`命令查看状态，发现该新创建并未提交的文件状态为**Untracked（未跟踪）**

![image-20241020221141101](https://pic.hibugs.net/NGBTEAM/image-20241020221141101.png)

​	使用`git add`命令将test01.txt文件放入暂存区

​	**ps：`git add .`可以提交所有被修改过的文件，一般要提交多个文件的时候可以使用`git add .`一次性提交到暂存区，而不需要`git add file1 file2...`这样去一个一个输入文件的名字 ☝🤓**

​	使用`git status`查看状态，文件处于将要被提交的状态，也就是我们说的放入了暂存区，还未提交到仓库

![image-20241020223611331](https://pic.hibugs.net/NGBTEAM/image-20241020223611331.png)

​	使用`git commit`提交放入暂存区的文件

- `git commit -m "说明..."` -m参数可以在后面添加你本次提交的说明，这个说明可以是对本次更新的介绍...

![image-20241020225831123](https://pic.hibugs.net/NGBTEAM/image-20241020225831123.png)

​	使用`vim`命令对文件进行修改并保存（你也可以使用文本编辑器对文件进行修改）-> 使用`git status`命令查看文件状态，文件为**not staged（未暂存状态）**，并且下面显示modified，与上面新创建文件显示的不同（上面未new file）

![image-20241020231332038](https://pic.hibugs.net/NGBTEAM/image-20241020231332038.png)

## 如何查看仓库（git log）

​	当我们使用`git commit`命令将文件从暂存区提交到仓库后，可以使用`git log`来查看提交记录，也就是版本

![image-20241020233027637](https://pic.hibugs.net/NGBTEAM/image-20241020233027637.png)

​	这里面记录了作者是谁（包括用户名和邮箱）

- 这里的用户名和邮箱就是git配置的`user.name`和`user.email`了

### 参数

`git log`的相关参数：

​	-all：显示所有分支

​	--pretty=oneline：将提交信息显示为一行

​	--abbrev-commit：简化commit信息

​	--graph：图形显示

## 回退版本（git reset）

​	`git reset --hard commitID`

​	使用`git log --pretty=oneline --abbrev-commit`我们可以看到有两次提交

- `git log --pretty=oneline --abbrev-commit`：这条命令可以让提交信息变得简短一些，其中commitID也会只截取前端部分，我们使用`git reset`回退版本的时候输入commitID可以是截取过后的commitID，也就是使用该命令展示的 20830a2 这个被截取后的commitID

![image-20241020235237976](https://pic.hibugs.net/NGBTEAM/image-20241020235237976.png)

​	我们使用`git reset`回退到第一次提交的版本（first commit），并且使用`git log`查看提交信息，发现版本已经回退到第一次提交时

![image-20241020235828905](https://pic.hibugs.net/NGBTEAM/image-20241020235828905.png)

### git reflog（查看历史记录）

​	回退版本之后提交记录会产生变化，但他并不是真的消失了，你可以使用`git reflog`来查看历史提交记录

## 忽略管理某些文件（.gitignore）

​	如果我们有文件不想让git管理，可以创建一个.gitignore的文件，在里面写上你不想让git管理的文件名，或是使用通配符（*.txt）这样以.txt结尾的所有文件都不会被git所管理，也就是这些文件不会被提交

​	创建1.txt文件，查看状态发现现在git会检测到该文件为**Untracked（未追踪）**

![image-20241021001009096](https://pic.hibugs.net/NGBTEAM/image-20241021001009096.png)

​	创建.gitignore文件，并在里面写入*.txt或者1.txt，再次查看状态，发现1.txt已经被忽略

![image-20241021001320108](https://pic.hibugs.net/NGBTEAM/image-20241021001320108.png)

# 命令总览

## git add

作用：将工作区的文件放入暂存区

`git add file1 file2...`：将文件1，文件2...放入暂存区

`git add .`：将所有文件放入暂存区

## git commit

作用：将暂存区的文件提交到仓库

### 参数

-m "说明"：该次更新的说明

## git status

作用：查看文件的状态

## git log

作用：查看提交记录

### 参数

-all：显示所有分支

--pretty=oneline：将提交信息显示为一行

--abbrev-commit：简化commit信息

--graph：图形显示
