package com.webcrawler.crawler.persistance;

import com.webcrawler.crawler.model.WebPage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebPageRepository extends JpaRepository<WebPage,Long> {
}
