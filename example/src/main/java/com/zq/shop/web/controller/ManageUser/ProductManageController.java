package com.zq.shop.web.controller.ManageUser;

import com.zq.app.server.DefaultUserDetails;
import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.bean.Product;
import com.zq.shop.web.service.IFileService;
import com.zq.shop.web.service.IProductService;
import com.zq.shop.web.vo.ProductVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/23 下午2:48
 * @Package com.zq.shop.web.controller.ManageUser
 **/

@Api(tags = "管理员：商品管理")
@RestController
@RequestMapping("/manage/product")
public class ProductManageController {

    @Autowired
    private IProductService iProductService;

    @Autowired
    private IFileService iFileService;

    @ApiOperation("商品列表")
    @PostMapping("/list")
    public ServerResponse list() {
        return iProductService.getProductList(0, 0);
    }

    @ApiOperation("商品创建或者更新")
    @PostMapping("/update")
    public ServerResponse create(@AuthenticationPrincipal DefaultUserDetails defaultUserDetails,
                                 @Valid ProductVo productVo) {

        Product target = new Product();
        BeanUtils.copyProperties(productVo, target);
        return iProductService.saveOrUpdateProduct(target, defaultUserDetails.getUid());
    }

    @ApiOperation("商品状态")
    @PostMapping("/status")
    public ServerResponse create(@NotEmpty Integer productId, @NotEmpty Integer status) {
        return iProductService.setSaleStatus(productId, status);
    }

    @ApiOperation("搜索商品")
    @ApiImplicitParam(value = "productId", type = "Integer", required = true)
    @PostMapping("/search")
    public ServerResponse search(Integer productId, String name) {
        return iProductService.searchProduct(name, productId, 0, 0);
    }

    @ApiOperation("上传商品图片")
    @PostMapping("/upload/image")
    public ServerResponse updateUserImage(@RequestParam("upload_file") MultipartFile file,
                                          HttpServletRequest httpServletRequest) {
        String path = httpServletRequest.getSession().getServletContext().getRealPath("upload");
        String uploadFile = iFileService.uploadFile(file, path);
        return ServerResponse.createBySuccess("上传成功", uploadFile);
    }

}
