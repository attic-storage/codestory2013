package codestory

class QuestionsTest extends GroovyTestCase {
    void testEmail() {
        def url = new URL("http://localhost:8080/?q=Quelle+est+ton+adresse+email")
        assert url.text =~ /^romain.lespinasse@gmail.com$/
    }
    void testMailingList() {
        def url = new URL("http://localhost:8080/?q=Es+tu+abonne+a+la+mailing+list(OUI/NON)")
        assert url.text =~ /^OUI$/
    }
    void testHappy() {
        def url = new URL("http://localhost:8080/?q=Es+tu+heureux+de+participer(OUI/NON)")
        assert url.text =~ /^OUI$/
    }
    void testMarkdown() {
        def url = new URL("http://localhost:8080/?q=Es+tu+pret+a+recevoir+une+enonce+au+format+markdown+par+http+post(OUI/NON)")
        assert url.text =~ /^OUI$/
    }
    void testAlwaysYes() {
        def url = new URL("http://localhost:8080/?q=Est+ce+que+tu+reponds+toujours+oui(OUI/NON)")
        assert url.text =~ /^NON$/
    }
    void testEnonce1() {
        def url = new URL("http://localhost:8080/?q=As+tu+bien+recu+le+premier+enonce(OUI/NON)")
        assert url.text =~ /^OUI$/
    }
    void testNightBugs() {
        def url = new URL("http://localhost:8080/?q=As+tu+passe+une+bonne+nuit+malgre+les+bugs+de+l+etape+precedente(PAS_TOP/BOF/QUELS_BUGS)")
        assert url.text =~ /^QUELS_BUGS$/
    }
    void testEnonce2() {
        def url = new URL("http://localhost:8080/?q=As+tu+bien+recu+le+second+enonce(OUI/NON)")
        assert url.text =~ /^OUI$/
    }
    void testNicolasDeloofCode() {
        def url = new URL("http://localhost:8080/?q=As+tu+copie+le+code+de+ndeloof(OUI/NON/JE_SUIS_NICOLAS)")
        assert url.text =~ /^NON$/
    }
    void testUltimateAnwser() {
        def url = new URL("http://localhost:8080/?q=Ultimate+Anwser")
        assert url.text =~ /^42$/
    }
}