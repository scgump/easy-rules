/*
 * The MIT License
 *
 *  Copyright (c) 2014, Mahmoud Ben Hassine (md.benhassine@gmail.com)
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

package io.github.benas.easyrules.samples.order;

import io.github.benas.easyrules.core.BasicRule;

import javax.management.MXBean;

/**
 * Business rule class that defines suspect order rule.
 *
 * @author Mahmoud Ben Hassine (md.benhassine@gmail.com)
 */
@MXBean
public class SuspectOrderRule extends BasicRule implements SuspectOrderJmxManagedRule {

    private float suspectOrderAmountThreshold = 1000;

    private Order order;

    private Customer customer;

    SuspectOrderRule(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean evaluateConditions() {
        return order.getAmount() > suspectOrderAmountThreshold && customer.isNew();
    }

    @Override
    public void performActions() throws Exception {
        System.out.println("Alert : A new customer [id=" + customer.getCustomerId() + "] has placed an order [id=" +
                order.getOrderId() + "] with amount " + order.getAmount() + " > " + suspectOrderAmountThreshold);
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public float getSuspectOrderAmountThreshold() {
        return suspectOrderAmountThreshold;
    }

    public void setSuspectOrderAmountThreshold(float suspectOrderAmountThreshold) {
        this.suspectOrderAmountThreshold = suspectOrderAmountThreshold;
    }

}
