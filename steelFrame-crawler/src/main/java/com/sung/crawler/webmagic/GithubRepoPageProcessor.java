package com.sung.crawler.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

/**
 * Created by sungang on 2016/8/29.
 */
public class GithubRepoPageProcessor implements PageProcessor {


    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setCharset("UTF-8").setRetryTimes(3).setSleepTime(1000);

    /**
     * process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
     *
     * @param page
     */
    @Override
    public void process(Page page) {

        //按照一定规则 添加待爬虫的URL到队列中去
        Html html = page.getHtml();
        page.addTargetRequests(html.links().regex("(https://github\\.com/\\w+/\\w+)").all());
        page.addTargetRequests(html.links().regex("(https://github\\.com/\\w+)").all());


        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        page.putField("name", html.xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
        if (page.getResultItems().get("name") == null) {
            //skip this page
            page.setSkip(true);
        }
        page.putField("readme", html.xpath("//div[@id='readme']/tidyText()"));

//        ResultItems resultItems = page.getResultItems();
//        System.out.println(resultItems.get("author"));
//        System.out.println(resultItems.get("name"));
//        System.out.println(resultItems.get("readme"));
    }

    @Override
    public Site getSite() {
        return site;
    }


    public static void main(String[] args) {
        //创建Spider
        Spider.create(new GithubRepoPageProcessor())
                //添加初始的URL
                .addUrl("https://github.com/code4craft")
                //添加一个Pipeline，一个Spider可以有多个Pipeline
                .addPipeline(new JsonFilePipeline("D:\\webmagic\\"))
                //开启n个线程
                .thread(5)
                //启动，会阻塞当前线程执行
                .run();
    }
}
