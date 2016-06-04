package org.intgame.model;

import javax.persistence.*;

/**
 * Created by vviital on 5/13/16.
 */

@Entity
@Table(name = "raw_htmls")
public class RawPage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "raw_html_id", nullable = false)
    private Long id;

    @Column(name = "html", columnDefinition = "MEDIUMTEXT")
    private String html;

    @Column(name = "url", unique = true)
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(url);
        buffer.append("\n");
        buffer.append(html);
        buffer.append("\n");
        return buffer.toString();
    }
}
