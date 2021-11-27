package com.example.phonebook.util;

@FunctionalInterface
public interface GenerateResponse<Code, Message, Body, ResponseType> {

    ResponseType generate(Code code, Message message, Body body);
}