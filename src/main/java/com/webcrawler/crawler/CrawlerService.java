package com.webcrawler.crawler;

import java.io.IOException;


public class CrawlerService {
    private String url;
    private Integer depth;
    private TreeNode<WebPage> root;

    public CrawlerService(String url, Integer depth){
        this.url = url;
        this.depth = depth;
        root = new TreeNode<WebPage>(new WebPage(url));
    }
    public void getChildLink() throws IOException {
        createChildLink(this.root,this.depth);
    }

    private void createChildLink(TreeNode<WebPage> root, Integer depth) throws IOException {
        if ( depth <= 0) return ;
        WebPage page = root.getData();
        for(WebPage p : page.findChildLinks()){
            TreeNode<WebPage> childPage = root.addChild(p);
            createChildLink(childPage,depth-1);
        }
    }
    protected TreeNode<WebPage> getRoot(){
        return this.root;
    }

    @Override
    public String toString(){
        return root.toString() ;
    }
}

