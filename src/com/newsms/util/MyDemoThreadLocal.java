package com.newsms.util;

/**
 * @author lmz
 */
public class MyDemoThreadLocal {
    String content;

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }


    public static void main(String[] args) {
        MyDemoThreadLocal demo = new MyDemoThreadLocal();

        for (int i = 0; i < 5; i++) {
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.setContent(Thread.currentThread().getName() + "线程");

                    System.out.println("---------------------");
                    System.out.println(Thread.currentThread().getName() + "--->" + demo.getContent());
                }
            });
            th.setName("线程" + i);
            th.start();
        }
    }
}
