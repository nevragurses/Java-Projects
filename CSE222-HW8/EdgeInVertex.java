/**This class to hold  source point and destination point of an edge.*/
public class EdgeInVertex{
        //Data fields.
        private int destination; //Destination point of an edge.
        private int source; //source point of an edge.

        /**
         * Data fields that initialize an edge.
         * @param source source point of an edge.
         * @param destination destination point of an edge.
         */
        public EdgeInVertex(int source,int destination){
            this.destination=destination;
            this.source=source;
        }
        /**
         * Gets destination point of an edge.
         * @return destination point.
         */
        public int getDestination(){
            return this.destination;
        }

        /**
         * Gets source point of an edge.
         * @return source point.
         */
        public int getSource(){
            return this.source;
        }

        /**
         * Overridden hashCode of this class.That include sum of hashcode of destination point
         * and hashCode of source point.
         * @return total hashcode.
         */
        @Override
        public int hashCode() {
            Integer forHashDest=destination;
            Integer forHashSource=source;
            int totalHashCode=forHashDest.hashCode()+forHashSource.hashCode();
            return totalHashCode;
        }

        /**
         * Overridden equals method.
         * @param obj given object.
         * @return comparing result.
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            EdgeInVertex temp = (EdgeInVertex) obj;
            if (destination==temp.destination && source==temp.source)
                return true;
            return false;
        }
        /**
         * To string method.
         * @return source point and destination point of an edge as string.
         */
        @Override
        public String toString(){
            return source + "  " + destination;
        }

}


