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
                response: {
                    b: "value b"
                }
            }, 
            {
                name: "second mock",
                method: "get", 
                endpoint: "/bar",
                response: {
                    d: "value d"
                }
            }
        ]
    };
}