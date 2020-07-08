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

package me.mrixs.shortlink.controller;

import lombok.AllArgsConstructor;
import me.mrixs.shortlink.model.Link;
import me.mrixs.shortlink.service.LinkService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@AllArgsConstructor
public class ApiController {

  private final LinkService linkService;

  @GetMapping("/l/{linkId}")
  public RedirectView goToLink(@PathVariable("linkId") String linkId) {
    Link link = linkService.getLongLink(linkId);
    return new RedirectView(link.getLongLink());
  }

  @PostMapping("/api/link")
  public Link shortLink(@RequestBody Link link) {
    String longLink = link.getLongLink();
    link = linkService.getShortLink(longLink);
    return link;
  }
}
