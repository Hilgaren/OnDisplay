/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.capstoneproject.dao;

import com.swcguild.capstoneproject.model.Authority;
import com.swcguild.capstoneproject.model.PinPost;
import com.swcguild.capstoneproject.model.Post;
import com.swcguild.capstoneproject.model.Tag;
import com.swcguild.capstoneproject.model.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class BlogDaoInMemImpl implements BlogDao {
    
    private Map<Integer, Post> blogPosts = new HashMap<>();
    private Map<Integer, PinPost>blogPinPosts = new HashMap<>();
    private static int postIdCounter = 0;
    private static int pinPostIdCounter = 0;
    
    
    public BlogDaoInMemImpl() {
    }

    @Override
    public User makeNewUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    @Override
    public Post addPost(Post post) {
        
        post.setPostID(postIdCounter);
        postIdCounter++;
        
        blogPosts.put(post.getPostID(), post);
        return post;
    }

    public Map<Integer, Post> getBlogPosts() {
        return blogPosts;
    }

    public void setBlogPosts(Map<Integer, Post> blogPosts) {
        this.blogPosts = blogPosts;
    }

    @Override
    public void removePost(int postId) {
       blogPosts.remove(postId);
    }

    @Override
    public void updatePost(Post post) {
       blogPosts.put(post.getPostID(), post);
    }

    @Override
    public List<Post> getAllPosts() {
        Collection<Post> c = blogPosts.values();
        return new ArrayList(c);
    }

    @Override
    public Post getPostById(int postId) {
        return blogPosts.get(postId);
    }

    @Override
    public String getTagsById(int postID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tag> getAllTags() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Post> getSearchPosts(String tag) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public void publishPost(int id, Post data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tag> getActiveTags() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Post> getActivePosts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public PinPost addPinPost(PinPost pinPost){
         pinPost.setPinPostID(pinPostIdCounter);
        pinPostIdCounter++;
        
        blogPinPosts.put(pinPost.getPinPostID(), pinPost);
        return pinPost;
        
    }

    public void removePinPost(int pinPostId){
        blogPinPosts.remove(pinPostId);
        
    }

    public void updatePinPost(PinPost pinPost){
        blogPinPosts.put(pinPost.getPinPostID(), pinPost);
        
    }

    public List<PinPost> getAllPinPosts(){
        Collection<PinPost> c = blogPinPosts.values();
        return new ArrayList(c);
    }

    public PinPost getPinPostById(int pinPostId){
        return blogPinPosts.get(pinPostId);
    }

    public void publishPinPost(int id, PinPost data){
       throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<PinPost> getActivePinPosts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getUserById(int userID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeUser(int userID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getAllUsers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Post> getAllPostObjects() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Authority getAuthorityByUsername(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void makeNewAuthority(Authority auth) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeAuthority(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Authority> getAllAuthorities() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Authority> getAuthoritiesByUsername(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
}
