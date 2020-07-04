/*
 * Copyright 2020 Vladimir Zagainov
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package me.mrixs.shortlink.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomStringImpl implements RandomString {
  private static final String BASE_58_FLICKR =
          "123456789abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";
  private final Random random = new Random();

  public String getRandomString(int length) {
    StringBuilder randomString = new StringBuilder();
    for (int i = 0; i < length; i++) {
      char randomChar = BASE_58_FLICKR.charAt(random.nextInt(BASE_58_FLICKR.length() - 1));
      randomString.append(randomChar);
    }
    return randomString.toString();
  }
}
