def String answerTo(String question) {
    def QA = [
            "Quelle est ton adresse email" : "romain.lespinasse@gmail.com",
            "Es tu abonne a la mailing list(OUI/NON)" : "OUI",
            "Es tu heureux de participer(OUI/NON)" : "OUI",
            "Es tu pret a recevoir une enonce au format markdown par http post(OUI/NON)" : "OUI",
            "Est ce que tu reponds toujours oui(OUI/NON)" : "NON",
            "As tu bien recu le premier enonce(OUI/NON)" : "OUI",
            "As tu passe une bonne nuit malgre les bugs de l etape precedente(PAS_TOP/BOF/QUELS_BUGS)" : "QUELS_BUGS",
            "As tu bien recu le second enonce(OUI/NON)" : "OUI",
            "As tu copie le code de ndeloof(OUI/NON/JE_SUIS_NICOLAS)": "NON"
    ]
    return QA[question]
}

def String calculate(String question) {
    def realQuestion = question
            .replace(" ", "+") // retablir le +
            .replace(",", ".") // Gérer les décimales françaises
    def anwser = evaluate(realQuestion)
    return anwser.toString()
            .replace(".", ",") // retablir les décimales françaises
            .replaceFirst(/,0+$/, "") // strip Trailling Zeros
}

def question = params.q

GroovySystem.println "La question est [" + question + "]"

def finalAnswer
switch (question) {
    case ~/^[0-9\/ \-\*\(\),]+$/:
        finalAnswer = calculate(question)
        break;
    default:
        def answer = answerTo(question)
        finalAnswer = answer != null ? answer : "42" // LA reponse
}
GroovySystem.println finalAnswer
println finalAnswer