package com.tingyu.venus.controller.client;

import com.tingyu.venus.common.CommonResult;
import com.tingyu.venus.service.GroupChatService;
import com.tingyu.venus.vo.GroupChatVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author essionshy
 * @Create 2020/11/22 11:29
 * @Version venus-server
 */
@RestController
@RequestMapping("api/client/chat/group")
public class GroupChatController {

    @Autowired
    private GroupChatService groupChatService;

    @PostMapping("create")
    public CommonResult create(@RequestBody GroupChatVo chatVo){
        groupChatService.save(chatVo);
        return CommonResult.ok();
    }

    @ApiOperation("根据群ID删除群")
    @DeleteMapping("delete/{groupId}")
    public CommonResult delete(@PathVariable("groupId") String groupId){
        groupChatService.deleteById(groupId);
        return CommonResult.ok();
    }

    @ApiOperation("根据群ID和成员ID将某个成员移除群")
    @DeleteMapping("remove/{groupId}/{contactId}")
    public CommonResult remove(@PathVariable("groupId")String groupId,@PathVariable("contactId")Long contactId){
        groupChatService.remove(groupId,contactId);

        return CommonResult.ok();

    }

}
