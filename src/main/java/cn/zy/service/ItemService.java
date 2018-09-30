package cn.zy.service;

import cn.zy.pojo.Item;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    private ItemFeignClient itemFeignClient;

    @HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod") // 进行容错处理
    public Item getItemById(String id) {
        return itemFeignClient.getItemById(id);
    }

    public Item queryItemByIdFallbackMethod(String id) { // 请求失败执行的方法
        return new Item(1L, "查询商品信息出错!", null, null, null);
    }
}
