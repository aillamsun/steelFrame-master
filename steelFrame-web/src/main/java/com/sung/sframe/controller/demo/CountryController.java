package com.sung.sframe.controller.demo;

import com.github.pagehelper.PageInfo;
import com.sung.sframe.common.controller.BaseController;
import com.sung.sframe.common.utils.LogUtils;
import com.sung.sframe.model.Country;
import com.sung.sframe.model.CountryQueryModel;
import com.sung.sframe.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * sungang
 */
@Controller
@RequestMapping("/demo/country")
public class CountryController extends BaseController<Country> {

    @Autowired
    private CountryService countryService;

    private String page_list = "list";

    @RequestMapping(value = {"list", "index", "index.html", ""})
    public String getList(Country country, @RequestParam(required = false, defaultValue = "1") int page,
                          @RequestParam(required = false, defaultValue = "10") int rows, Model model) {
        List<Country> countryList = countryService.selectByCountry(country, page, rows);
        PageInfo pageInfo = new PageInfo<Country>(countryList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("queryParam", country);
        model.addAttribute("page", page);
        model.addAttribute("rows", rows);
        return viewName(page_list);
    }

    /**
     * 测试pageinfo，参数必须包含pageNum和pageSize
     * 这个方法以及调用的相关方法看起来可能很难理解，如果不理解，就不要使用这种方式!
     * @param queryModel
     * @return
     */
    @RequestMapping(value = "pageInfo", produces = "application/json")
    public ModelAndView pageInfo(CountryQueryModel queryModel) {
        ModelAndView result = new ModelAndView();
        PageInfo<Country> pageInfo = countryService.selectByCountryQueryModel(queryModel);
        result.addObject("pageInfo", pageInfo);
        return result;
    }

    @RequestMapping(value = "view", method = RequestMethod.GET)
    public ModelAndView view(Country country,Model model) {
        ModelAndView result = new ModelAndView();
        if (country.getId() != null) {
            country = countryService.selectByKey(country.getId());
        }
        model.addAttribute("country", country);
        return result;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Country country) {
        if (country.getId() != null) {
            countryService.updateAll(country);
        } else {
            countryService.insert(country);
        }
        return redirectToUrl(page_list);
    }

    @RequestMapping("delete")
    public String delete(Integer id) {
        countryService.delete(id);
        return redirectToUrl(page_list);
    }

}
