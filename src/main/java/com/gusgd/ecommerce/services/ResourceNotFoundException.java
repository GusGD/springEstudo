package com.gusgd.ecommerce.services;

public class ResourceNotFoundException  extends RuntimeException{
  public ResourceNotFoundException(String message){
    super(message);
  }
}
