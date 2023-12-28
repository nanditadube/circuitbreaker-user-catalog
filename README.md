This is a sample project implementing circuit breaker in spring boot using resilience4j. 
The User service fetches from Catalog service, which is another independent microservice. If Catalog service is down, and a failure happens, then there is a fallback method that gives default result.
However, after the threshold failure is reached, the circuit gets open to disallow more calls to User service to avoid cascading of failure.
Then the circuit is half open, allowing 1 call, to see if catalog service is fine again. Duration is 5 seconds in half open state. If success, then circuit closes again.
And application runs as usual. This is meant to introduce fault tolerance in springboot applications in case of failure among micro services communicating with each other.
