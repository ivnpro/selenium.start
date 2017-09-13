package com.support.helper;

public class Linker {
    static final String stage = "0";
    public static final String adminLink = "http://admin."+stage+".ts.wiggum.tf/";
    public static final String agencyLink = "http://partners."+stage+".ts.wiggum.tf/";
    public static final String chatLink = String.format("http://chat.%s.ts.wiggum.tf/", stage);

 }

