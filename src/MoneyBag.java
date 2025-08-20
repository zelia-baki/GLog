import java.util.Vector;

class MoneyBag implements IMoney {
    private Vector<Money> fMonies = new Vector<Money>();

    MoneyBag(Money m1, Money m2) {
        appendMoney(m1);
        appendMoney(m2);
    }

    MoneyBag(Money bag[]) {
        for (int i = 0; i < bag.length; i++)
            appendMoney(bag[i]);
    }

    private void appendMoney(Money m) {
        if (m.amount() == 0) return; // Don't add zero amounts
        
        if (fMonies.isEmpty()) {
            fMonies.add(m);
        } else {
            int i = 0;
            while ((i < fMonies.size()) && 
                   (!(fMonies.get(i).currency().equals(m.currency()))))
                i++;
            if (i >= fMonies.size()) {
                fMonies.add(m);
            } else {
                Money existingMoney = fMonies.get(i);
                Money newMoney = new Money(existingMoney.amount() + m.amount(), m.currency());
                if (newMoney.amount() == 0) {
                    fMonies.remove(i); // Remove if sum is zero
                } else {
                    fMonies.set(i, newMoney);
                }
            }
        }
    }

    public IMoney add(IMoney m) {
        return m.addMoneyBag(this);
    }

    public IMoney addMoney(Money aMoney) {
        MoneyBag result = new MoneyBag(new Money[0]);
        // Copy all existing monies
        for (Money money : fMonies) {
            result.appendMoney(money);
        }
        // Add the new money
        result.appendMoney(aMoney);
        return result.simplify();
    }

    public IMoney addMoneyBag(MoneyBag aMoneyBag) {
        MoneyBag result = new MoneyBag(new Money[0]);
        // Copy all monies from this bag
        for (Money money : fMonies) {
            result.appendMoney(money);
        }
        // Copy all monies from the other bag
        for (Money money : aMoneyBag.fMonies) {
            result.appendMoney(money);
        }
        return result.simplify();
    }

    // Simplify MoneyBag to Money if it contains only one currency
    private IMoney simplify() {
        if (fMonies.size() == 1) {
            return fMonies.get(0);
        }
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        
        MoneyBag other = (MoneyBag) obj;
        if (fMonies.size() != other.fMonies.size()) return false;
        
        // Check if all monies match (order independent)
        for (Money money : fMonies) {
            boolean found = false;
            for (Money otherMoney : other.fMonies) {
                if (money.equals(otherMoney)) {
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (Money money : fMonies) {
            hash += money.hashCode();
        }
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < fMonies.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(fMonies.get(i).toString());
        }
        sb.append("}");
        return sb.toString();
    }
}