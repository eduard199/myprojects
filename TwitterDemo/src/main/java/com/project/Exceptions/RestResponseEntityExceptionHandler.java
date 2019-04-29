package com.project.Exceptions;

import com.project.Exceptions.AlreadyLikedThisPost;
import com.project.Exceptions.DuplicateIdAndFollowindUserId;
import com.project.Exceptions.DuplicateUsername;
import com.project.Exceptions.UserNotExisting;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {SameUserIdAndFollowingUserId.class})
    protected ResponseEntity<Object> SameUserAndFollowingUserId(Exception ex, WebRequest request)
    {
        String bodyOfResponse = "Same user id and following user id";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    @ExceptionHandler(value = {DuplicateIdAndFollowindUserId.class})
    protected ResponseEntity<Object> DuplicateIdAndFollowindUserId(Exception ex, WebRequest request)
    {
        String bodyOfResponse = "Pair userId and followingUserId already exists in table followers";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    @ExceptionHandler(value = {DuplicateUsername.class})
    protected ResponseEntity<Object> DuplicateUsername(Exception ex, WebRequest request)
    {
        String bodyOfResponse = "This username already exists";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    @ExceptionHandler(value = {UsernameNotFound.class})
    protected ResponseEntity<Object> UsernameNotFound(Exception ex, WebRequest request)
    {
        String bodyOfResponse = "Username not found";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    @ExceptionHandler(value = {UserNotExisting.class})
    protected ResponseEntity<Object> UserNotExisting(Exception ex, WebRequest request)
    {
        String bodyOfResponse = "The user was not found";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    @ExceptionHandler(value = {AlreadyLikedThisPost.class})
    protected ResponseEntity<Object> AlreadyLikedThisPost(Exception ex, WebRequest request)
    {
        String bodyOfResponse = "You already liked that post";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}