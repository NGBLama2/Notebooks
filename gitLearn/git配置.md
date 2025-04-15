# git配置⚙

## 基本配置 🛠

1. 打开git bash

2. 设置用户信息
   - `git config --global user.name "你的用户名"`
   - `git config --global user.email "你的邮箱"`
   
3. 查看配置信息
   - `git clone --global user.name`（查看用户名）
   - `git clone --global user.email`（查看邮箱）
   
   邮箱可以乱写（只要保证邮箱格式正确即可），这只是作为你的标识用于在代码追溯中使用

## 额外配置 🔧

### 解决Git Bash乱码问题

`git config --global core.quotepath false`