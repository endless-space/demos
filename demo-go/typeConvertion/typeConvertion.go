package main

import (
	"fmt"
	"strconv"
)

func main() {
	var a int = 65
	b := string(a)

	fmt.Println(b)

	var num int = 66
	numLiteral := strconv.Itoa(num)
	fmt.Println(numLiteral)
}
