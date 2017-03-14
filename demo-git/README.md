Git Note
========

## Git是一个 分布式 版本控制系统
  分布式意味着对于每一个人的电脑上都是一个完整的版本库(Repository). 
  但是依然存在一台充当"中央版本库"的电脑, 方便大家推送修改.

## Git理论
  Git跟踪管理的是修改而非文件.
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
  git分支的目的主要是隔离工作


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
8. 回退到最近一次add 或 commit时的状态 或 回复文件
	git checkout -- [file]
9. 将暂存区内容撤回至工作区
	git reset HEAD [file]
10. 删除文件
	git rm [file]
11. 添加远程仓库
	git remote add origin git@github.com:endless-space/demos.git
12. 推送本地仓库的分支内容至远程仓库的分支内容
	git push -u origin master:master
13. 创建并切换分支
	git checkout -b dev 等价于
		git branch dev
		git checkout dev
14. 删除分支
	git branch -d dev
15. 合并分支
	git merge dev

