package com.bimowo.first.controller;

import com.bimowo.first.model.Product;
import com.bimowo.first.service.ProductService;
import com.bimowo.first.utils.CommonQueryBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/productview")
public class ProductViewController {
    @Autowired
    ProductService productService;

    @RequestMapping("/listProduct")
    public String listProduct(@RequestParam(value = "name",defaultValue = "") String name,
                              Model model, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                              @RequestParam(value = "pageSize", defaultValue = "3") int pageSize) throws Exception {
        Product record = new Product();
        if (StringUtils.isNotEmpty(name)) {
            record.setName(name);
            model.addAttribute("name", name);
        }
        //计算总数量
        long total = productService.count(record);
        //计算总页数
        int totalPageNum = (int)total / pageSize;
        if (total % pageSize > 0) totalPageNum ++;
        //分页相关参数
        CommonQueryBean query= new CommonQueryBean();
        query.setPageSize(pageSize);
        query.setPageNum(pageNum);
        query.setStart((pageNum-1) * pageSize);
        List<Product> list = productService.list4Page(record, query);
        model.addAttribute("total", total);
        model.addAttribute("totalPage", totalPageNum);
        model.addAttribute("list", list);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageNum", pageNum);

        return "index";
    }

    @RequestMapping(value = "/add")
    public String  add(){
        return "add";

    }
    @RequestMapping(value = "/addProduct")
    public String listUser(Product product,Model model){
        productService.save(product);
        return "redirect:listProduct";
    }
    @RequestMapping(value = "update")
    public String update(Long id,Model model){
        Product product = productService.selectByPrimaryKey(id);
        model.addAttribute("product",product);

        return "update";
    }
    @RequestMapping("/updateProduct")
    public String update(Product product,Model model){
        productService.updateItem(product);
        return "redirect:listProduct";

    }
    @RequestMapping("/delete")
    public String delete(Long id) throws Exception {
        productService.deleteByPrimaryKey(id);
        return "redirect:listProduct";
    }
    @RequestMapping("/getProduct")
    public String getProduct(Long id, Model model) throws Exception {
        Product product = productService.selectByPrimaryKey(id);
        model.addAttribute("product", product);
        return "productShow";
    }

}
