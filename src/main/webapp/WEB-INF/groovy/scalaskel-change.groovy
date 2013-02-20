// Extract du nombre de cents
def pathInfo = request.getPathInfo()
// Transformation de "/X" en X
def cents = pathInfo[1..(pathInfo.length() - 1)].toInteger()

// Liste des permutations possibles
def permutations = [[21, 11, 7, 1], [11, 7, 1], [21, 7, 1], [21, 11, 1], [21, 1], [11, 1], [7, 1], [1]]
// Mapping nom de pieces/valeurs en cents
def changeMapping = [21:"baz", 11: "qix", 7:"bar", 1:"foo"]
def changes = []

permutations.each { permutation ->
    def permutationChanges = []

    permutation.each { permutationItem ->
        if (permutationChanges.size() == 0) {
            def count = 0
            while (permutationItem * count <= cents ) {
                count++
            }
            count--
            if (count > 0) {
                if(permutationItem > 1) {
                    for( i in 1..count ) {
                        def changeItem = [:]
                        def sumItem = permutationItem * i
                        changeItem[changeMapping.find {it.key == permutationItem}.value] = i
                        def permutationChange = [sum:sumItem, change:changeItem]
                        permutationChanges << permutationChange
                    }
                } else {
                    def changeItem = [:]
                    def sumItem = permutationItem * count
                    changeItem[changeMapping.find {it.key == permutationItem}.value] = count
                    def permutationChange = [sum:sumItem, change:changeItem]
                    permutationChanges << permutationChange
                }
            }
        } else {
            def newPermutationChanges = []
            for( item in permutationChanges ) {
                def count = 0
                while (item.sum + permutationItem * count <= cents ) {
                    count++
                }
                count--
                if (count > 0) {
                    if(permutationItem > 1) {
                        for( i in 1..count ) {
                            def changeItem = item.change.clone()
                            def sumItem = item.sum + permutationItem * i
                            changeItem[changeMapping.find {it.key == permutationItem}.value] = i
                            def permutationChange = [sum:sumItem, change:changeItem]
                            newPermutationChanges << permutationChange
                        }
                    } else {
                        def changeItem = item.change.clone()
                        def sumItem = item.sum + permutationItem * count
                        changeItem[changeMapping.find {it.key == permutationItem}.value] = count
                        def permutationChange = [sum:sumItem, change:changeItem]
                        newPermutationChanges << permutationChange
                    }
                } else {
                    newPermutationChanges << item
                }
            }

            permutationChanges = newPermutationChanges
        }
    }

    permutationChanges.each { changes << it.change }
}

// renvoie en json des répartitions possibles
response.contentType = 'application/json'

def jsonBuilder = new groovy.json.JsonBuilder()
jsonBuilder.setContent(changes.unique())
GroovySystem.println jsonBuilder.toString()
println jsonBuilder.toString()