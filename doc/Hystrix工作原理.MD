##### 工作原理
- 当调用出现错误时，开启一个时间窗口(默认10秒)
- 在这个时间窗口内，统计调用次数是否达到最小请求数?
  - 如果没有达到，则重置统计信息，回到第一步<br>
  - 如果达到了，则统计失败的请求数占所有请求数的百分比，是否达到阈值?
    - 如果达到，则跳闸(不再请求对应的服务)
    - 如果没有达到，则重置信息信息，回到第一步
- 如果跳闸则会开启一个活动窗口(默认5秒)，每隔5秒，Hystrix会让一个请求通过，到达那个苦苦挣扎的服务，看是否调用成功?
  - 如果成功，重置断路器，回到第三步
  - 如果不成功，回到第三步


##### 可以修改的参数 - 自定义断路器行为
- 出现错误时时间窗口的长度
- 最小请求数
- 错误请求的百分比
- 跳闸后，活动窗口的长度