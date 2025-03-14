package com.myTraining1.core.models;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
import javax.jcr.NodeIterator;
import javax.jcr.Node;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BooksModel {

    private List<Book> books = new ArrayList<>();

    public BooksModel(Resource resource) {
        System.out.println("📢 BooksModel Initialized!");

        ResourceResolver resolver = resource.getResourceResolver();
        Session session = resolver.adaptTo(Session.class);

        if (session == null) {
            System.out.println("❌ ERROR: Session is null!");
            return;
        }

        try {
            QueryManager queryManager = session.getWorkspace().getQueryManager();
            String queryString = "SELECT * FROM [cq:Page] AS book WHERE ISDESCENDANTNODE(book, '/content/myTraining1/books0')";
            Query query = queryManager.createQuery(queryString, Query.JCR_SQL2);
            QueryResult result = query.execute();

            NodeIterator nodes = result.getNodes(); // ✅ FIXED
            while (nodes.hasNext()) {
                Node bookNode = nodes.nextNode();
                Resource bookResource = resolver.getResource(bookNode.getPath());
                if (bookResource != null) {
                    books.add(new Book(bookResource));
                    System.out.println("📖 Book Added -> " + bookNode.getPath());
                }
            }

            System.out.println("✅ Total books fetched: " + books.size());

        } catch (Exception e) {
            System.out.println("❌ ERROR fetching books: " + e.getMessage());
        }
    }

    public List<Book> getBooks() {
        return books;
    }

    public static class Book {
        private String title;
        private String text;
        private String image;

        public Book(Resource bookPage) {
            Resource titleRes = bookPage.getChild("jcr:content/root/title");
            Resource textRes = bookPage.getChild("jcr:content/root/text");
            Resource imageRes = bookPage.getChild("jcr:content/root/image/file");

            ValueMap titleMap = titleRes != null ? titleRes.getValueMap() : null;
            ValueMap textMap = textRes != null ? textRes.getValueMap() : null;
            ValueMap imageMap = imageRes != null ? imageRes.getValueMap() : null;

            this.title = titleMap != null ? titleMap.get("jcr:title", "No Title") : "No Title";
            this.text = textMap != null ? textMap.get("text", "No Description") : "No Description";
            this.image = imageMap != null ? imageMap.get("fileReference", "/content/dam/default-image.jpg") : "/content/dam/default-image.jpg";

            System.out.println("📖 Book Added -> Title: " + this.title + ", Image: " + this.image);
        }

        public String getTitle() { return title; }
        public String getText() { return text; }
        public String getImage() { return image; }
    }
}
