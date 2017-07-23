# Hoshimori Project

This is meant to be used as a database processor for the main [project](https://github.com/kokonguyen191/Hoshimori-Project)

## How to use?

### Prerequisites

```
OpenCSV 3.10: http://opencsv.sourceforge.net/
```

### Quick start


1. You will need two databases in **.csv** format:
    - Official Japanese version from [dengekionline](https://wiki.dengekionline.com/battlegirl/)
    - Chinese version from [zh.battlegirl](https://zh.battlegirl.wikia.com/)

2. Crawl them with [Hoshimori-Scrapy](https://github.com/kokonguyen191/Hoshimori-Scrapy)

3. Specify the paths in CrawledDataCsvParser.java for three files:
    - Japanese database in line 55
    - Chinese database in line 87
    - Output file in line 959

    *All of them MUST be in **.csv** format*

4. Execute CrawledDataCsvParser.java

5. If there are any untranslated output in the log, add them to the dictionaries in Dictionary.java

## Authors & Translators

* **[Koko191](https://github.com/kokonguyen191)**

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE) file for details
