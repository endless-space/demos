Git Note
========

## Git是一个 分布式 版本控制系统
分布式意味着对于每一个人的电脑上都是一个完整的版本库(Repository). 但是依然存在一台充当"中央版本库"的电脑, 方便大家推送修改.

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
6.