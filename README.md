# Introduction 
  Using Thread Pool for calling api without blocking main thread.<br>
  Development Environment: `IntelliJ`.<br>
  Programming Language: `Java`.
  

# AsyncWithExecute Branch
Using thread pool with maximumPoolSize is 2, so when calling 3 GET http requests, this program uses 2 threads (id=12,11) to call api, any threads (id=11) has completed to 
get data, it continues to call the rest. 
According to below image, this implement does not block main thread, 
because this program can print "Do something when calling api!" before fetching data successfully.<br>

![image](https://user-images.githubusercontent.com/52070609/180614530-6ab011ca-a89e-4c6c-94ab-c029c9f70c50.png)

**Problem**:
  1. Using threadPool.execute, we cannot handle the responses of apis
  2. After calling api, thread id 11 still waits for the results 
but I expect this thread will be release after calling successfully and continue to call the rest







# AsyncWithInvokeAll Branch
Using InvokeAll, I can handle data after fetching but it blocks main thread...

![image](https://user-images.githubusercontent.com/52070609/180614763-697983b4-3d8b-4e09-a053-4982b14abdca.png)






