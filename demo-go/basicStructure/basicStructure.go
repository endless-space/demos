package main

import (
	"fmt"
)

var variable = "1213"

// PI 圆周率
const PI = 3.14
const name = "aaaa"

// 类型别名
type newType int

// 结构类型
type structType struct{}

// 接口类型
type closeable interface{}

func main() {
	strVar := "MySQL"

	fmt.Println("Hello, world!" + " " + strVar)
}
