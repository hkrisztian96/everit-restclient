package org.everit.atlassian.restclient.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.annotation.Generated;

import org.everit.http.client.HttpMethod;

/**
 * A rest request that can be sent to a remote endpoint with one of the CallEndpoint parameters of
 * {@link RestCallUtil}.
 */
public final class RestRequest {

  /**
   * Builder to build {@link RestRequest}.
   */
  @Generated("SparkTools")
  public static final class Builder {
    private String basePath;

    private Map<String, String> headers = Collections.emptyMap();

    private HttpMethod method = HttpMethod.GET;

    private String path;

    private Map<String, String> pathParams = Collections.emptyMap();

    private Map<String, Collection<String>> queryParams = Collections.emptyMap();

    private Optional<Object> requestBody = Optional.empty();

    private Builder() {
    }

    private Builder(RestRequest restRequest) {
      this.basePath = restRequest.basePath;
      this.headers = restRequest.headers;
      this.method = restRequest.method;
      this.path = restRequest.path;
      this.pathParams = restRequest.pathParams;
      this.queryParams = restRequest.queryParams;
      this.requestBody = restRequest.requestBody;
    }

    public Builder basePath(String basePath) {
      this.basePath = basePath;
      return this;
    }

    public RestRequest build() {
      return new RestRequest(this);
    }

    public Builder headers(Map<String, String> headers) {
      this.headers = headers;
      return this;
    }

    public Builder method(HttpMethod method) {
      this.method = method;
      return this;
    }

    public Builder path(String path) {
      this.path = path;
      return this;
    }

    public Builder pathParams(Map<String, String> pathParams) {
      this.pathParams = pathParams;
      return this;
    }

    public Builder queryParams(Map<String, Collection<String>> queryParams) {
      this.queryParams = queryParams;
      return this;
    }

    public Builder requestBody(Optional<Object> requestBody) {
      this.requestBody = requestBody;
      return this;
    }
  }

  /**
   * Creates builder to build {@link RestRequest}.
   *
   * @return created builder
   */
  @Generated("SparkTools")
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Creates a builder to build {@link RestRequest} and initialize it with the given object.
   *
   * @param restRequest
   *          to initialize the builder with
   * @return created builder
   */
  @Generated("SparkTools")
  public static Builder builderFrom(RestRequest restRequest) {
    return new Builder(restRequest);
  }

  private final String basePath;

  private final Map<String, String> headers;

  private final HttpMethod method;

  private final String path;

  private final Map<String, String> pathParams;

  private final Map<String, Collection<String>> queryParams;

  private final Optional<Object> requestBody;

  @Generated("SparkTools")
  private RestRequest(Builder builder) {
    this.basePath = builder.basePath;
    this.headers = Collections.unmodifiableMap(new HashMap<>(builder.headers));
    this.method = builder.method;
    this.path = builder.path;
    this.pathParams = Collections.unmodifiableMap(new HashMap<>(builder.pathParams));
    this.queryParams = Collections.unmodifiableMap(new HashMap<>(builder.queryParams));
    this.requestBody = builder.requestBody;
  }

  /**
   * Builds the path that will be used by this request including the path parameters.
   */
  public String buildPathWithPathParams() {
    String pathWithPathParams = this.path != null ? this.path : "";
    for (Entry<String, String> pathParam : this.pathParams.entrySet()) {
      pathWithPathParams =
          pathWithPathParams.replace('{' + pathParam.getKey() + '}', pathParam.getValue());
    }
    return pathWithPathParams;
  }

  /**
   * Builds the full URL that will be used to send this request, including the basePath, the path
   * with the injected path parameters and the query parameters.
   */
  public String buildURI() {
    StringBuilder url = new StringBuilder(this.basePath != null ? this.basePath : "");

    if (this.basePath == null || this.basePath.endsWith("/")) {
      url.append('/');
    }

    String pathWithPathParams = buildPathWithPathParams();

    if (pathWithPathParams.startsWith("/")) {
      url.append(pathWithPathParams, 1, pathWithPathParams.length());
    } else {
      url.append(pathWithPathParams);
    }

    char separatorChar = url.toString().contains("?") ? '&' : '?';

    for (Entry<String, Collection<String>> queryParam : this.queryParams.entrySet()) {
      for (String paramValue : queryParam.getValue()) {
        url.append(separatorChar);
        if (separatorChar != '&') {
          separatorChar = '&';
        }

        try {
          url.append(URLEncoder.encode(queryParam.getKey(), "UTF-8")).append('=')
              .append(URLEncoder.encode(paramValue, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
          throw new RuntimeException(e);
        }
      }
    }

    return url.toString();
  }

  public String getBasePath() {
    return this.basePath;
  }

  public Map<String, String> getHeaders() {
    return this.headers;
  }

  public HttpMethod getMethod() {
    return this.method;
  }

  public String getPath() {
    return this.path;
  }

  public Map<String, String> getPathParams() {
    return this.pathParams;
  }

  public Map<String, Collection<String>> getQueryParams() {
    return this.queryParams;
  }

  public Optional<Object> getRequestBody() {
    return this.requestBody;
  }

}
