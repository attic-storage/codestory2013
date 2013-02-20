package codestory.jajascript

class Optimizer {
    static def optimize(def orders) {
        def possiblePlannings = []

        def permutationGenerator = new PermutationGenerator(orders);
        while(permutationGenerator.hasNext()) {
            def permutationItem = permutationGenerator.next()
            def possiblePlanning = [gain: 0, path: [], orders: [],
                    hours: [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]]
            permutationItem.each {
                def beginHour = it.get("DEPART")
                def endHour = beginHour + it.get("DUREE") - 1
                if(possiblePlanning.gain == 0) {
                    for(hour in beginHour..endHour) {
                        possiblePlanning.hours[hour] = 1
                    }
                    possiblePlanning.gain = it.get("PRIX")
                    possiblePlanning.orders << it
                } else {
                    def possible = true
                    for(hour in beginHour..endHour) {
                        if (possiblePlanning.hours[hour] == 1) {
                            possible = false
                            break;
                        }
                    }
                    if (possible) {
                        for(hour in beginHour..endHour) {
                            possiblePlanning.hours[hour] = 1
                        }
                        possiblePlanning.gain += it.get("PRIX")
                        possiblePlanning.orders << it
                    }
                }
            }
            possiblePlanning.orders.sort { it.get("DEPART") }
            possiblePlanning.orders.each {
                possiblePlanning.path << it.get("VOL")
            }
            possiblePlannings << possiblePlanning
        }

        def optimizedPlanning = [gain: 0, path: []]
        for( possiblePlanning in possiblePlannings ) {
            if (possiblePlanning.gain > optimizedPlanning.gain) {
                optimizedPlanning.gain = possiblePlanning.gain
                optimizedPlanning.path = possiblePlanning.path
            }
        }
        return optimizedPlanning
    }
}
