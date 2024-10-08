package com.techzen.techlearn.controller;

import com.techzen.techlearn.dto.request.UserRequestDTO;
import com.techzen.techlearn.dto.request.UserRequestDTO2;
import com.techzen.techlearn.dto.response.ApiResponse;
import com.techzen.techlearn.enums.ErrorCode;
import com.techzen.techlearn.service.UserService;
import com.techzen.techlearn.util.JsonResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/users")
public class UserController {

    UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUser(@RequestParam(required = false, defaultValue = "1") int page,
                                     @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return JsonResponse.ok(userService.getAllUser(page, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable UUID id) {
        return JsonResponse.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestParam(value = "avatar", required = false) MultipartFile multipartFile,
                                     @ModelAttribute @Valid UserRequestDTO2 request) {
        return JsonResponse.ok(userService.createUser(request,multipartFile));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable UUID id, @RequestParam(value = "avatar", required = false) MultipartFile multipartFile,
                                        @ModelAttribute @Valid UserRequestDTO2 request) {
        return JsonResponse.ok(userService.updateUserDTO(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        userService.deleteUserById(id);
        return JsonResponse.deleted();
    }

}
