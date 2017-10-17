public interface IRestClient
{


    TResponse Get<TResponse>(string relativeUri) where TResponse : class, new();

    
    TResponse Post<TResponse, TCommand>(string relativeUri, TCommand command) where TResponse : class, new();

    TResponse Put<TResponse, TCommand>(string relativeUri, TCommand command) where TResponse : class, new();

}