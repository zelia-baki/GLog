class Money implements IMoney {
    private int fAmount;
    private String fCurrency;

    public Money(int amount, String currency) {
        fAmount = amount;
        fCurrency = currency;
    }

    public int amount() {
        return fAmount;
    }

    public String currency() {
        return fCurrency;
    }

    public IMoney add(IMoney m) {
        return m.addMoney(this);
    }

    public IMoney addMoney(Money aMoney) {
        if (aMoney.currency().equals(currency()))
            return new Money(amount() + aMoney.amount(), currency());
        return new MoneyBag(this, aMoney);
    }

    public IMoney addMoneyBag(MoneyBag aMoneyBag) {
        return aMoneyBag.addMoney(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        
        Money other = (Money) obj;
        return fAmount == other.fAmount && 
               fCurrency != null && fCurrency.equals(other.fCurrency);
    }

    @Override
    public int hashCode() {
        return fAmount * 31 + (fCurrency != null ? fCurrency.hashCode() : 0);
    }

    @Override
    public String toString() {
        return "[" + fAmount + " " + fCurrency + "]";
    }
}
