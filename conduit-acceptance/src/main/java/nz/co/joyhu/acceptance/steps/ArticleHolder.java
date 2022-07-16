package nz.co.joyhu.acceptance.steps;

import nz.co.joyhu.Generated;
import nz.co.joyhu.acceptance.domain.Article;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class ArticleHolder extends GenericHolder<List<Article>> {

    @Generated
    public ArticleHolder() {
    }

}
