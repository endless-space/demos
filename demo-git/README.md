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
  git发布时使用标签标记commit_id, 获得某次的全部状态
    

## Git实践
0. 基本配置
	git config --global color.ui true
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
	git log --graph --pretty=oneline --abbrev-commit
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
	git remote -v
12. 推送本地仓库的分支内容至远程仓库的分支内容
	git push -u origin master:master
13. 创建并切换分支
	git checkout -b dev 等价于
		git branch dev
		git checkout dev
14. 删除分支
	git branch -d dev
15. 合并分支
	git merge dev (fast-forward模式的分支合并)
	git merge --no-ff -m "merge branch" dev
16. 暂存恢复工作区内容
	git stash
	git stash list
	git stash pop
	git stash drop
	git stash apply

## 最佳实践
分支管理策略:
	master分支用于发布版本, 不用于开发
	dev分支用于开发, 发布时合并到master分支.
	每个开发都有自己的分支, 是不是合并到dev分支上.
	bug的处理使用bug分支, 确定在那个分支上修改bug, 最终合并至那个分支.
	在切回创建bug分支之前stash当前分支的内容, 稍后恢复.
	feature分支和bug分支类似.

多人协作的工作模式通常是这样：
	首先，可以试图用git push origin branch-name推送自己的修改；
	如果推送失败，则因为远程分支比你的本地更新，需要先用git pull试图合并；
	如果合并有冲突，则解决冲突，并在本地提交；
	没有冲突或者解决掉冲突后，再用git push origin branch-name推送就能成功！
	如果git pull提示“no tracking information”，则说明本地分支和远程分支的链接关系没有创建.
		用命令git branch --set-upstream branch-name origin/branch-name

