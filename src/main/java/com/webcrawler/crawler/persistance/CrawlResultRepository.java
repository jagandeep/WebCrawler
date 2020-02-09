package com.webcrawler.crawler.persistance;

import com.webcrawler.crawler.model.CrawlResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrawlResultRepository extends JpaRepository<CrawlResult,Long> {
}
