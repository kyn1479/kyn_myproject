package com.kyn.myproject.demo.common.dto;

/**
 * @author Kangyanan
 * @Description: 证书内容
 * @date 2021/1/20 17:20
 */
public class FileContent {
    private Long id;
    private String name;
    private byte[] content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
