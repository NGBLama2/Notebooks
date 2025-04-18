# Tensor的属性

每一个Tensor都有三种属性：

- torch.dtype：tensor中元素的数据类型
- torch.device：标识了tensor对象创建之后存储的设备名称（cpu还是gpu(cuda)）
- torch.layout：表示tensor内存布局的对象

## 稀疏的矩阵和稠密的矩阵

稀疏和稠密的定义是取决于该矩阵中非零元素的个数，若零元素个数越多，那么该矩阵就是越稀疏的矩阵；反之，则为越稠密的矩阵

如果有一个100x100的矩阵，他里面只有两个非零元素，那么我们只需要存储他两个非零元素的坐标即可，这样可以节省空间；并且稀疏的矩阵他的参数个数少，也就意味着模型会更加简单

之前说过模型中会含有未知的参数，Y = WX + B类似于这样，通常未知的参数使用变量去代替。如果有一个式子他的未知参数很多，Y = AX^n + BX^n-1 + ... + ZX^n-m + ... 那么他的模型就会越复杂，因为式子的结果会受这些参数而影响，简而言之他的不确定性就会更高，在线性代数中矩阵可以转化为方程式的写法（一条方程式就是一条约束，而一个参数就是一个自由度）若约束的数量=自由度 那么就可以解得参数（方程式就会有唯一解），若约束小于自由度，则会有一系列的解（也就是没有唯一的解），所以参数越多模型则会越复杂，也就是自由度太高，模型就会越复杂