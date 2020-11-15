const array1 = [1, 2, 3, 4, 5], array2 = [3, 4, 5, 6, 7]

// 并集
let union = array1.concat(array2.filter((value => {return !(array1.indexOf(value) > - 1)})))
// 交集
let intersection = array1.filter(value => {return array2.indexOf(value) > -1})

console.log(intersection)