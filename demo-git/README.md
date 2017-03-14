Git Note
========

## Git是一个 分布式 版本控制系统
  分布式意味着对于每一个人的电脑上都是一个完整的版本库(Repository). 
  但是依然存在一台充当"中央版本库"的电脑, 方便大家推送修改.

## Git理论
  由于每次commit都生成一个新版本, commit_id也是版本id.
  版本库中HEAD指针指向当前版本
  	HEAD^    表示上一版本
  	HEAD^^   表示上上个版本
  	HEAD~100 表示上100个版本
  工作区是区别于.git目录的目录.
  版本库就是.git目录, 由以下内容构成:
  	暂存区(stage):
  	自动创建的第一个分支: master
  	指向master的指针: HEAD
  add 添加动作将修改从工作区添加至暂存区
  commit 提交动作将暂存区内容提交到当前分支


## Git实践
1. 创建版本库
	git init
2. 查看版本库状态
	git status
3. 查看修改
	git diff [file]
4. 添加文件(修改)至仓库
	git add
	git add .
5. 提交文件(修改)至仓库
	git commit
	git commit -m "提交说明"
6. 查看提交日志和命令日志
	git log
	git reflog 
7. 切换版本(回退)版本
	git reset --hard commit_id

