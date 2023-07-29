export function load() {
    return {
        title: "The title",
        request: {
            first: 'first value', 
            second: 'second value'
        }, 
        mocks: [
            {
                name: "first mock",
                method: "get", 
                endpoint: "/foo",
                request: {
                    a: "value a"
                },
                response: {
                    b: "value b"
                }
            }, 
            {
                name: "second mock",
                method: "get", 
                endpoint: "/bar",
                request: {
                    c: "value c"
                },
                response: {
                    d: "value d"
                }
            }
        ]
    };
}