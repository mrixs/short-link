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

import lombok.AllArgsConstructor;
import me.mrixs.shortlink.model.Link;
import me.mrixs.shortlink.repository.LinkRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class LinkServiceImpl implements LinkService {
  private final RandomString randomString;
  private final LinkRepository linkRepository;

  @Override
  public Link getShortLink(String longLink) {
    UUID id = UUID.randomUUID();
    String shortLink = randomString.getRandomString(6);
    Link link = linkRepository.findByShortLink(shortLink);
    if (link != null && longLink.equals(link.getLongLink())) return link;
    while (link != null) {
      shortLink = randomString.getRandomString(6);
      link = linkRepository.findByShortLink(shortLink);
    }
    link = new Link(id, longLink, shortLink);
    return linkRepository.save(link);
  }

  @Override
  public Link getLongLink(String shortLink) {
    return linkRepository.findByShortLink(shortLink);
  }
}