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

import me.mrixs.shortlink.model.Link;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LinkServiceImplTest {
  @Autowired
  private LinkService linkService;

  @Test
  void getLongLink() {
    String longLink = "http://localhost";
    Link testShortlink = linkService.getShortLink(longLink);
    Link testLongLink = linkService.getLongLink(testShortlink.getShortLink());
    assertEquals(longLink, testLongLink.getLongLink());
    assertEquals(testShortlink.getLongLink(), testLongLink.getLongLink());
  }
}
